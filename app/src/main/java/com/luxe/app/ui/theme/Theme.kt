package com.luxe.app.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = RichPurple,
    onPrimary = Color.White,
    primaryContainer = DeepViolet,
    onPrimaryContainer = AccentLight,
    secondary = SoftCyan,
    onSecondary = Color.Black,
    secondaryContainer = Color(0xFF455A64),
    onSecondaryContainer = SoftCyan,
    tertiary = Accent,
    onTertiary = Color.White,
    tertiaryContainer = Color(0xFF6A1B9A),
    onTertiaryContainer = AccentLight,
    error = Color(0xFFCF6679),
    onError = Color.Black,
    errorContainer = Color(0xFF93000A),
    onErrorContainer = Color(0xFFFFDADA),
    background = DarkPurple,
    onBackground = TextPrimary,
    surface = Surface,
    onSurface = OnSurface,
    surfaceVariant = DeepViolet,
    onSurfaceVariant = TextSecondary,
    outline = Color(0xFF6A4C93),
    outlineVariant = Color(0xFF4A3A7F)
)

@Composable
fun LuxeTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = Typography,
        content = content
    )
}