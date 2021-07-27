package de.darthkali.weefood.domain.model

data class RecipeIngredient(
    val id              : Int,
    val quantity        : Int,
    val unit            : Int,
    val recipe_id       : Int,
    val ingredient_id   : Int,
)
