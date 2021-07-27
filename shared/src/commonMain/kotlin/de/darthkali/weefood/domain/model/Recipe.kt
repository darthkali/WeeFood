package de.darthkali.weefood.domain.model

data class Recipe(
    val id            : Int,
    val name          : String,
    val image         : String? = "",
    val cooking_time  : Int,
    val unit          : String,
    val description   : String? = ""
)
