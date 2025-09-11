package dev.aurakai.collabcanvas.network

import com.google.gson.Gson
import dev.aurakai.collabcanvas.model.CanvasElement
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okio.ByteString
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
    private val okHttpClient: OkHttpClient,
    private val gson: Gson,
) {
    // Removed TAG property
    private var webSocket: WebSocket? = null
    private val _events = MutableSharedFlow<CanvasWebSocketEvent>()
    val events: SharedFlow<CanvasWebSocketEvent> = _events.asSharedFlow()

        /**
         * Called when the WebSocket connection is successfully established.
         *
         * Emits a CanvasWebSocketEvent.Connected to the service's event stream.
         *
         * @param webSocket The newly opened WebSocket.
         * @param response The HTTP handshake response from the server.
         */
            Timber.d("WebSocket connection opened")
            _events.tryEmit(CanvasWebSocketEvent.Connected)
        }

        /**
         * Parse an incoming text payload and emit the corresponding CanvasWebSocketEvent.
         *
         * Attempts to deserialize the JSON `text` into a CanvasWebSocketMessage and emits
         * CanvasWebSocketEvent.MessageReceived on success. If deserialization fails, emits
         * CanvasWebSocketEvent.Error with the parse error message.
         *
         * @param text The received text payload (expected JSON representing a CanvasWebSocketMessage).
         */
            Timber.d("Message received: $text") // Changed to Timber
            try {
                val message = gson.fromJson(text, CanvasWebSocketMessage::class.java)
                _events.tryEmit(CanvasWebSocketEvent.MessageReceived(message))
            } catch (e: Exception) {
                Timber.e(
                    e,
                ) // Changed to Timber, added exception first for stack trace
                _events.tryEmit(CanvasWebSocketEvent.Error("Error parsing message: ${e.message}"))
            }
        }

            Timber.d("Binary message received") // Changed to Timber
            _events.tryEmit(CanvasWebSocketEvent.BinaryMessageReceived(bytes))
        }

            Timber.d("WebSocket closing: $code / $reason") // Changed to Timber
            _events.tryEmit(CanvasWebSocketEvent.Closing(code, reason))
        }

            Timber.d("WebSocket closed: $code / $reason") // Changed to Timber
            _events.tryEmit(CanvasWebSocketEvent.Disconnected)
        }

            Timber.e(t, "WebSocket error") // Changed to Timber
            _events.tryEmit(CanvasWebSocketEvent.Error(t.message ?: "Unknown error"))
        }
    }

    fun connect(url: String) {
        if (webSocket != null) {
            Timber.w("WebSocket already connected") // Changed to Timber
            return
        }

        val request = Request.Builder()
            .url(url)
            .build()

        webSocket = okHttpClient.newWebSocket(request, webSocketListener)
    }

    /**
     * Closes the active WebSocket connection (if any) and clears the stored reference.
     *
     * If a connection exists, it is closed with normal closure code 1000 and reason "User initiated disconnect".
     * If no connection is active, the call is a no-op.
     */
    fun disconnect() {
        webSocket?.close(1000, "User initiated disconnect")
        webSocket = null
    }

    /**
     * Serialize a CanvasWebSocketMessage to JSON and send it over the active WebSocket connection.
     *
     * If a connection is active the message is converted to JSON with the injected Gson instance and
     * enqueued for sending. Serialization or send failures (including missing connection) result in
     * a false return value; the function does not throw.
     *
     * @param message The canvas message to serialize and send.
     * @return true if the message was successfully queued for sending by the WebSocket, false if no
     * connection exists or an error occurred during serialization or send.
     */
            val json = gson.toJson(message)
            webSocket?.send(json) ?: run {
                Timber.e("WebSocket is not connected") // Changed to Timber
                false
            }
        } catch (e: Exception) {
            Timber.e(
                e,
            ) // Changed to Timber, added exception first for stack trace
            false
        }
    }

}

sealed class CanvasWebSocketEvent {
    object Connected : CanvasWebSocketEvent()

    object Disconnected : CanvasWebSocketEvent()
}

sealed class CanvasWebSocketMessage {
    abstract val type: String
    abstract val canvasId: String
    abstract val userId: String
    abstract val timestamp: Long
}

data class ElementAddedMessage(
    override val canvasId: String,
    override val userId: String,
    override val timestamp: Long = System.currentTimeMillis(),
    val element: CanvasElement,
) : CanvasWebSocketMessage() {
    override val type: String = "ELEMENT_ADDED"
}

data class ElementUpdatedMessage(
    override val canvasId: String,
    override val userId: String,
    override val timestamp: Long = System.currentTimeMillis(),
    val elementId: String,
    val updates: Map<String, Any>,
) : CanvasWebSocketMessage() {
    override val type: String = "ELEMENT_UPDATED"
}

data class ElementRemovedMessage(
    override val canvasId: String,
    override val userId: String,
    override val timestamp: Long = System.currentTimeMillis(),
    val elementId: String,
) : CanvasWebSocketMessage() {
    override val type: String = "ELEMENT_REMOVED"
}
