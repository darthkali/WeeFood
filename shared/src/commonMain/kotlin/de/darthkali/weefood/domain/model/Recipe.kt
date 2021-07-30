package de.darthkali.weefood.domain.model

import de.darthkali.weefood.domain.util.enums.CookingTimeUnit

data class Recipe(
    val id                  : Int = 0,
    val name                : String,
    val image               : String? = "",
    val cooking_time        : Int,
    val cooking_time_unit   : String,
    val description         : String? = ""
){
    override fun toString(): String {
        return StringBuilder()
            .append("|id: ")
            .append(this.id)
            .append("| name: ")
            .append(this.name)
            .append("| image: ")
            .append(this.image)
            .append("| cooking_time: ")
            .append(this.cooking_time)
            .append("| unit: ")
            .append(this.cooking_time_unit)
            .append("| description: ")
            .append(this.description)
            .append("|")
            .toString()
    }
}
