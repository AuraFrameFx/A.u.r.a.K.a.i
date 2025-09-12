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
