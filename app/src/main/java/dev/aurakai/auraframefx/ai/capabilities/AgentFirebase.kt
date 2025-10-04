package dev.aurakai.auraframefx.ai.capabilities

import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.remoteConfig
import com.google.firebase.remoteconfig.remoteConfigSettings
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.storage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * A secure wrapper around Firebase services that enforces capability policies.
 * All Firebase operations must go through this class to ensure proper access control.
 */
@Singleton
class AgentFirebase @Inject constructor(
    private val policy: CapabilityPolicy,
    private val firebaseApp: FirebaseApp
) {
    private val firestore: FirebaseFirestore by lazy { Firebase.firestore(firebaseApp) }
    private val storage: FirebaseStorage by lazy { Firebase.storage(firebaseApp) }
    private val remoteConfig: FirebaseRemoteConfig by lazy {
        Firebase.remoteConfig.apply {
            setConfigSettingsAsync(remoteConfigSettings {
                minimumFetchIntervalInSeconds = if (firebaseApp.isDefaultApp) 3600 else 0
                fetchTimeoutInSeconds = 30
            })
        }
    }
    private val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance(firebaseApp) }

    /**
         * Retrieve the Firestore document data for the given collection and document ID.
         *
         * The call enforces read scope and collection access policy, and validates the document size.
         *
         * @return `Map<String, Any>` containing the document data, or `null` if the document does not exist.
         * @throws SecurityException If the document data size exceeds the policy's maximum allowed size.
         */

    suspend fun getDocument(collection: String, docId: String): Map<String, Any>? =
        withContext(Dispatchers.IO) {
            policy.requireScope(CapabilityPolicy.SCOPE_FIRESTORE_READ)
            policy.validateCollectionAccess("$collection/$docId")

            firestore.collection(collection).document(docId).get().await()
                ?.data
                ?.also { validateDocumentSize(it) }
        }

    /**
     * Persists a Firestore document at the specified collection/document path.
     *
     * Requires the Firestore write capability, validates access to the target path, and validates
     * the serialized size of `data` before saving it.
     *
     * @param collection The Firestore collection path to store the document in.
     * @param docId The document identifier within the collection.
     * @param data The document fields to save as a map.
     * @param merge Present for API compatibility; currently not applied and the provided `data` replaces the document.
     * @throws SecurityException If the serialized size of `data` exceeds the policy's maximum document size.
     */
    suspend fun saveDocument(
        collection: String,
        docId: String,
        data: Map<String, Any>,
        merge: Boolean = true
    ) = withContext(Dispatchers.IO) {
        policy.requireScope(CapabilityPolicy.SCOPE_FIRESTORE_WRITE)
        policy.validateCollectionAccess("$collection/$docId")
        validateDocumentSize(data)

        firestore.collection(collection).document(docId).set(data).await()
    }

    /**
     * Obtain a CollectionReference for the specified Firestore collection path while enforcing read scope and validating access.
     *
     * @param collectionPath The Firestore collection path to access.
     * @return A `CollectionReference` pointing to the specified collection.
     */
    fun collection(collectionPath: String): CollectionReference {
        policy.requireScope(CapabilityPolicy.SCOPE_FIRESTORE_READ)
        policy.validateCollectionAccess(collectionPath)
        return firestore.collection(collectionPath)
    }

    /**
     * Provides a Firestore DocumentReference for the given document path after validating access.
     *
     * Access checks: verifies that the caller has read permission for Firestore and that the specified
     * collection/document path is allowed by the policy before returning the reference.
     *
     * @param documentPath The full path to the Firestore document (e.g., "collection/docId" or nested path).
     * @return The `DocumentReference` for the specified document path.
     */
    fun document(documentPath: String): DocumentReference {
        policy.requireScope(CapabilityPolicy.SCOPE_FIRESTORE_READ)
        policy.validateCollectionAccess(documentPath)
        return firestore.document(documentPath)
    }

    /**
     * Uploads a byte array to the specified storage path and returns the file's download URL.
     *
     * Validates the upload scope and storage path before performing the upload. If `metadata` is provided,
     * each map entry is applied as custom metadata on the stored object.
     *
     * @param path The storage path where the file will be uploaded.
     * @param data The file contents as a byte array.
     * @param metadata Optional custom metadata keys and values to attach to the stored object.
     * @return The download URL for the uploaded file as a string.
     */

    suspend fun uploadFile(
        path: String,
        data: ByteArray,
        metadata: Map<String, String> = emptyMap()
    ) = withContext(Dispatchers.IO) {
        policy.requireScope(CapabilityPolicy.SCOPE_STORAGE_UPLOAD)
        policy.validateStoragePath(path)

        val ref = storage.reference.child(path)
        val uploadTask = ref.putBytes(data)
        uploadTask.await()

        // Update metadata if provided
        if (metadata.isNotEmpty()) {
            ref.updateMetadata(
                com.google.firebase.storage.StorageMetadata.Builder()
                    .apply {
                        metadata.forEach { (key, value) ->
                            setCustomMetadata(key, value)
                        }
                    }
                    .build()
            ).await()
        }

        ref.downloadUrl.await().toString()
    }

    /**
     * Download the object stored at the given Firebase Storage path.
     *
     * @param path The storage path of the object to download.
     * @return The downloaded object's data as a byte array.
     */
    suspend fun downloadFile(path: String): ByteArray = withContext(Dispatchers.IO) {
        policy.requireScope(CapabilityPolicy.SCOPE_STORAGE_DOWNLOAD)
        policy.validateStoragePath(path)

        val bytes = storage.reference.child(path).getBytes(Long.MAX_VALUE).await()
        bytes
    }

    /**
     * Obtains a StorageReference for the specified storage path after enforcing download scope and validating the path.
     *
     * @param path The path within the Firebase Storage bucket to reference.
     * @return A `StorageReference` pointing to the given path.
     */
    fun getStorageReference(path: String): StorageReference {
        policy.requireScope(CapabilityPolicy.SCOPE_STORAGE_DOWNLOAD)
        policy.validateStoragePath(path)
        return storage.reference.child(path)
    }

    /**
     * Fetches and activates Remote Config values from Firebase.
     *
     * @return `true` if fetched values were activated, `false` otherwise.
     */

    suspend fun fetchRemoteConfig() = withContext(Dispatchers.IO) {
        policy.requireScope(CapabilityPolicy.SCOPE_CONFIG_READ)
        remoteConfig.fetchAndActivate().await()
    }

    /**
     * Retrieve the Remote Config value for the specified key while enforcing the config-read capability.
     *
     * @param key The Remote Config key to read.
     * @return The value for the given key as a `String`.
     */
    fun getConfigValue(key: String): String {
        policy.requireScope(CapabilityPolicy.SCOPE_CONFIG_READ)
        return remoteConfig.getString(key)
    }

    /**
     * Retrieve the currently authenticated Firebase user.
     *
     * This operation enforces the `SCOPE_AUTH_MANAGE` capability via the configured policy.
     *
     * @return The currently signed-in `FirebaseUser`, or `null` if no user is authenticated.
     */

    suspend fun getCurrentUser() = withContext(Dispatchers.Main) {
        policy.requireScope(CapabilityPolicy.SCOPE_AUTH_MANAGE)
        auth.currentUser
    }

    /**
     * Ensures the serialized size of the provided document map does not exceed the policy's maximum.
     *
     * @param data The document data to validate.
     * @throws SecurityException if the serialized document size (in bytes) is greater than policy.maxDocumentSize; the exception message includes the actual and maximum sizes.
     */

    private fun validateDocumentSize(data: Map<String, Any>) {
        val size = data.toString().toByteArray().size.toLong()
        if (size > policy.maxDocumentSize) {
            throw SecurityException("Document size $size bytes exceeds maximum allowed ${policy.maxDocumentSize} bytes")
        }
    }

    companion object {
        /**
         * Creates an AgentFirebase instance configured with the capability policy for the given agent type.
         *
         * @param agentType The agent type whose capability policy will be applied.
         * @param firebaseApp The FirebaseApp to use; defaults to the default Firebase app.
         * @return An AgentFirebase configured with the selected CapabilityPolicy and provided FirebaseApp.
         * @throws IllegalArgumentException If no policy is defined for the provided agentType.
         */
        fun createWithPolicy(
            agentType: AgentType,
            firebaseApp: FirebaseApp = FirebaseApp.getInstance()
        ): AgentFirebase {
            val policy = when (agentType) {
                AgentType.AURA -> CapabilityPolicy.AURA_POLICY
                AgentType.KAI -> CapabilityPolicy.KAI_POLICY
                AgentType.GENESIS -> CapabilityPolicy.GENESIS_POLICY
                AgentType.CASCADE -> CapabilityPolicy.CASCADE_POLICY
                else -> throw IllegalArgumentException("No policy defined for agent type: $agentType")
            }
            return AgentFirebase(policy, firebaseApp)
        }
    }
}