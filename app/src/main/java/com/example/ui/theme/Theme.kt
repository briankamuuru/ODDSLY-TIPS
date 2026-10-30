package com.example.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val OddslyColorScheme = lightColorScheme(
    primary = BluePrimary,
    onPrimary = PureWhite,
    primaryContainer = BluePrimaryLight,
    onPrimaryContainer = BluePrimary,
    background = PureWhite,
    onBackground = TextPrimary,
    surface = PureWhite,
    onSurface = TextPrimary,
    surfaceVariant = BlueBoxBg,
    onSurfaceVariant = TextSecondary,
    outline = BlueBoxBorderSubtle
)

@Composable
fun MyApplicationTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = OddslyColorScheme,
        typography = Typography,
        content = content
    )
}
