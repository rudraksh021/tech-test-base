package com.cleanarch.clean.util.preview

import androidx.compose.runtime.Composable
import com.cleanarch.clean.ui.theme.AppTheme

@Composable
fun PreviewContainer(
    content: @Composable () -> Unit
) {
    AppTheme {
        content()
    }
}