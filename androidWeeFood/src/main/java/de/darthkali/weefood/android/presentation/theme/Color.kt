package de.darthkali.weefood.android.presentation.theme

import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color

val Primary = Color(0xFF356859)             // DarkGreen
val Primary_Variant = Color(0xFFB9E4C9)     // LightGreen
val Secondary = Color(0xFFFD5523)           // Orange
val Background = Color(0xFFFFFBE6)          // YellowColoredWhite
val Surface = Color(0xFFFFFDF3)             // White


public val DarkColors = darkColors(
    primary = Primary,
    primaryVariant = Primary_Variant,
    secondary = Secondary,
    background = Background,
    surface = Surface,
)
public val LightColors = lightColors(
    primary = Primary,
    primaryVariant = Primary_Variant,
    secondary = Secondary,
    background = Background,
    surface = Surface,
)