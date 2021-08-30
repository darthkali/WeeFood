package de.darthkali.weefood.theme

import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color

val DarkGreen = Color(0xFF356859)             // DarkGreen
val LightGreen = Color(0xFFB9E4C9)     // LightGreen
val Orange = Color(0xFFFD5523)           // Orange
val LightYellow = Color(0xFFFFFBE6)          // YellowColoredWhite
val White = Color(0xFFFFFDF3)             // White

val Black = Color(0xFF000000)             // DarkGreen
val DarkGray = Color(0xFF1B1B1B)             // DarkGreen
val LightGray = Color(0xFF5C5C5C)     // LightGreen

val DarkColors = darkColors(
    primary = Black,
    primaryVariant = LightGray,
    secondary = Orange,
    background = DarkGray,
    surface = LightGray,
    onPrimary = White,
    onSecondary = White,
    onBackground = White,
    onSurface = White,
    )

val LightColors = lightColors(
    primary = DarkGreen,
    primaryVariant = LightGreen,
    secondary = Orange,
    background = LightYellow,
    surface = White,
    onPrimary = LightYellow,
    onSecondary = LightYellow,
    onBackground = DarkGreen,
    onSurface = DarkGreen,
)