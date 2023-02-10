package com.sevenpeakssoftware.base.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

private val DarkColorPalette =
        darkColors(primary = Black, primaryVariant = Black, secondary = White, surface = Black)

private val LightColorPalette =
        lightColors(primary = Black, primaryVariant = Black, secondary = White, surface = Black
                
                /* Other default colors to override
                background = Color.White,
                surface = Color.White,
                onPrimary = Color.White,
                onSecondary = Color.Black,
                onBackground = Color.Black,
                onSurface = Color.Black,
                */)

@Composable
fun CarsTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }
    
    MaterialTheme(colors = colors, typography = Typography, shapes = Shapes, content = content)
}

@Preview(name = "Light Theme", showBackground = true)
@Composable
fun CarsLightThemePreview(darkTheme: Boolean = false) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }
    
    MaterialTheme(colors = colors,
            typography = Typography,
            shapes = Shapes,
            content = { Scaffold(topBar = { }, content = {}) })
}