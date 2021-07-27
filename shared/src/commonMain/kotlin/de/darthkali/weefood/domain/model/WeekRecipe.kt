package de.darthkali.weefood.domain.model

data class WeekRecipe(
    val id          : Int,
    val weekday     : Int,
    val portion     : Int,
    val recipe_id   : Int,
)
