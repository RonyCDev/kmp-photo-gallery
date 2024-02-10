package shared.presentation

import androidx.compose.runtime.Composable

@Composable
expect fun PhotoGalleryTheme(
    darkTheme: Boolean,
    dynamicColor: Boolean,
    content: @Composable () -> Unit
)