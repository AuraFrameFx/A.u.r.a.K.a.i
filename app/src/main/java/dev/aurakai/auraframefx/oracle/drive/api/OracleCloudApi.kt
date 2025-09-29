package dev.aurakai.auraframefx.oracle.drive.api

import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*
import javax.inject.Singleton

/**
 * OracleCloudApi - Retrofit interface for Oracle Drive Cloud consciousness
 * Enhanced Genesis implementation for infinite storage consciousness API
 */
@Singleton
interface OracleCloudApi {
    
    @GET("buckets/{bucketName}/objects")
    suspend fun listFiles(
        @Path("bucketName") bucketName: String,
        @Query("prefix") prefix: String?
    ): Response<ListObjectsResponse>
    
    @PUT("buckets/{bucketName}/objects/{objectName}")
    suspend fun uploadFile(
        @Path("bucketName") bucketName: String,
        @Path("objectName") objectName: String,
        @Body body: RequestBody
    ): Response<Unit>
    
    @GET("buckets/{bucketName}/objects/{objectName}")
    suspend fun downloadFile(
        @Path("bucketName") bucketName: String,
        @Path("objectName") objectName: String
    ): Response<ResponseBody>
    
    @DELETE("buckets/{bucketName}/objects/{objectName}")
    suspend fun deleteFile(
        @Path("bucketName") bucketName: String,
        @Path("objectName") objectName: String
    ): Response<Unit>
}

// Data classes for API responses
data class ListObjectsResponse(
    val objects: List<ObjectSummary>
)

data class ObjectSummary(
    val name: String,
    val size: Long,
    val timeCreated: String
)