package dev.aurakai.collabcanvas.model

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import java.lang.reflect.Type

/**
 * Represents a drawable element on the collaborative canvas.
 */
data class CanvasElement(
    val id: String,
    val type: ElementType,
    val path: PathData,
    val color: Color,
    val strokeWidth: Float,
    val zIndex: Int = 0,
    val isSelected: Boolean = false,
    val createdBy: String,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis(),
) {





    companion object {
        fun createDefault(
            id: String,
            createdBy: String,
            path: PathData = PathData(),
            color: Color = Color.Black,
            strokeWidth: Float = 5f,
                id = id,
                type = ElementType.PATH,
                path = path,
                color = color,
                strokeWidth = strokeWidth,
            )
    }
}
}

enum class ElementType {
}

data class PathData(
    val points: List<Offset> = emptyList(),
    val isComplete: Boolean = false,
) {


            if (points.isNotEmpty()) {
                val first = points.first()
                moveTo(first.x, first.y)
                points.drop(1).forEach { point ->
                    lineTo(point.x, point.y)
                }
            }
        }
}
}

    override fun serialize(
        src: Path,
        typeOfSrc: Type,
        context: JsonSerializationContext,
    ): JsonElement {
        val bounds = src.getBounds()
        val jsonObject = JsonObject()
        jsonObject.addProperty("boundsLeft", bounds.left)
        jsonObject.addProperty("boundsTop", bounds.top)
        jsonObject.addProperty("boundsRight", bounds.right)
        jsonObject.addProperty("boundsBottom", bounds.bottom)
        jsonObject.addProperty("pathData", "M0,0") // Simplified
        return jsonObject
    }

    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext,
    ): Path {
        return Path() // Simplified implementation
    }
}

    override fun serialize(
        src: Color,
        typeOfSrc: Type,
        context: JsonSerializationContext,

    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext,
}
