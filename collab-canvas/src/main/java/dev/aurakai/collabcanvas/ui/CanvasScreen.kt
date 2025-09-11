package dev.aurakai.collabcanvas.ui


/**
*/
@Composable
fun CanvasScreen() {
Box(
modifier = Modifier
.fillMaxSize()
.background(MaterialTheme.colorScheme.background)
) {
)
}
}
}

// Extension to draw grid
private fun DrawScope.drawGrid() {
val gridSpacing = 50f
val strokeWidth = 1f
val color = Color.Gray.copy(alpha = 0.3f)

// Draw vertical lines
for (x in 0..size.width.toInt() step gridSpacing.toInt()) {
drawLine(
color = color,
start = Offset(x.toFloat(), 0f),
end = Offset(x.toFloat(), size.height),
strokeWidth = strokeWidth
)
}

// Draw horizontal lines
for (y in 0..size.height.toInt() step gridSpacing.toInt()) {
drawLine(
color = color,
start = Offset(0f, y.toFloat()),
end = Offset(size.width, y.toFloat()),
strokeWidth = strokeWidth
)
