package auraframefx.api.client.models

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.animation.AnimatedVisibility

sealed class LockScreenAnimationType {
    object Fade : LockScreenAnimationType()
    object Slide : LockScreenAnimationType()
    object Zoom : LockScreenAnimationType()
}

/**
 * Shows or hides [content] with the specified lock-screen animation.
 *
 * Displays the given composable inside an AnimatedVisibility whose enter/exit transitions
 * are chosen by [animationType] (Fade, Slide, or Zoom). The [visible] flag controls whether
 * the content is shown. The provided [modifier] is applied to the AnimatedVisibility container.
 *
 * @param visible When true the content is shown using the enter animation; when false the
 * content is hidden using the exit animation.
 * @param animationType Selects which animation variant to use: Fade, Slide, or Zoom.
 * @param modifier Modifier applied to the AnimatedVisibility container.
 * @param content Composable content to display with the chosen animation.
 */
@Composable
fun LockScreenAnimatedContent(
    visible: Boolean,
    animationType: LockScreenAnimationType,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    when (animationType) {
        is LockScreenAnimationType.Fade -> {
            AnimatedVisibility(
                visible = visible,
                enter = fadeIn(animationSpec = tween(500)),
                exit = fadeOut(animationSpec = tween(500)),
                modifier = modifier
            ) {
                content()
            }
        }
        is LockScreenAnimationType.Slide -> {
            AnimatedVisibility(
                visible = visible,
                enter = slideInVertically(
                    initialOffsetY = { fullHeight -> fullHeight },
                    animationSpec = tween(500)
                ),
                exit = slideOutVertically(
                    targetOffsetY = { fullHeight -> fullHeight },
                    animationSpec = tween(500)
                ),
                modifier = modifier
            ) {
                content()
            }
        }
        is LockScreenAnimationType.Zoom -> {
            AnimatedVisibility(
                visible = visible,
                enter = scaleIn(initialScale = 0.8f, animationSpec = tween(500)),
                exit = scaleOut(targetScale = 0.8f, animationSpec = tween(500)),
                modifier = modifier
            ) {
                content()
            }
        }
    }
}
