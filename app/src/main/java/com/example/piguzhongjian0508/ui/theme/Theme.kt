package com.example.piguzhongjian0508.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = SoftAccent,
    secondary = SoftMuted,
    tertiary = SoftAccentLight,
    background = SoftBackground,
    surface = SoftBackground,
    surfaceContainer = SoftBackground,
    surfaceContainerLow = SoftBackground,
    surfaceVariant = SoftBackground,
    onPrimary = Color.White,
    onSecondary = SoftForeground,
    onTertiary = Color.White,
    onBackground = SoftForeground,
    onSurface = SoftForeground,
    onSurfaceVariant = SoftMuted,
    outline = SoftMuted.copy(alpha = 0.45f),
)

@Composable
fun Piguzhongjian0508Theme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
