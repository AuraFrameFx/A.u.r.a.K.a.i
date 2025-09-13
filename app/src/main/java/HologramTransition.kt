package auraframefx.api.client.models

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.foundation.layout.Box

/**
 * HologramTransition composable for futuristic lockscreen or UI transitions.
 * Combines fade, scale, and optional color/blur for a holographic effect.
 */
/**
 * A composable that applies a holographic-like transition by animating scale and alpha.
 *
 * When `visible` is true the content animates from the start scale/alpha to the end scale/alpha;
 * when false it animates back. Initial animated values match `visible` to avoid a visual jump
 * on first composition. Animations use a tween with the provided duration.
 *
 * @param visible Controls whether the content is in the visible (end) state or the hidden (start) state.
 * @param modifier Modifier applied to the container; the animated scale and alpha are applied via a graphicsLayer.
 * @param durationMillis Total duration of the scale and alpha animations, in milliseconds.
 * @param startScale Scale value used when transitioning to the hidden state (default 0.85f).
 * @param endScale Scale value used when transitioning to the visible state (default 1f).
 * @param startAlpha Alpha value used when transitioning to the hidden state (default 0.3f).
 * @param endAlpha Alpha value used when transitioning to the visible state (default 1f).
 * @param content Composable content displayed inside the transition.
 */
@Composable
fun HologramTransition(
    visible: Boolean,
    modifier: Modifier = Modifier,
    durationMillis: Int = 700,
    startScale: Float = 0.85f,
    endScale: Float = 1f,
    startAlpha: Float = 0.3f,
    endAlpha: Float = 1f,
    content: @Composable () -> Unit
) {
    val scale = remember { Animatable(if (visible) startScale else endScale) }
    val alpha = remember { Animatable(if (visible) startAlpha else endAlpha) }

    LaunchedEffect(visible) {
        scale.animateTo(if (visible) endScale else startScale, animationSpec = tween(durationMillis))
        alpha.animateTo(if (visible) endAlpha else startAlpha, animationSpec = tween(durationMillis))
    }

    Box(
        modifier = modifier
            .graphicsLayer {
                this.scaleX = scale.value
                this.scaleY = scale.value
                this.alpha = alpha.value
                // Optionally add blur or color filter for more hologram effect
            }
    ) {
        content()
    }
}
