package dev.aurakai.auraframefx.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

/**
 * Applies a Material 3 color scheme to the given composable content based on the `useDarkTheme` flag.
 *
 * @param useDarkTheme When `true`, applies the dark color scheme; when `false`, applies the light color scheme. Defaults to the system dark theme preference.
 * @param content The composable UI content that will be wrapped with the selected theme.
 */
@Composable
fun AuraAppTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (useDarkTheme) darkColorScheme() else lightColorScheme()
    MaterialTheme(colorScheme = colors, content = content)
}