package de.darthkali.weefood.domain.model

import de.darthkali.weefood.domain.util.enums.Weekday

data class WeekRecipe(
    val id          : Int = 0,
    val weekday     : Weekday,
    val portion     : Int,
    val recipe_id   : Int,
){
    override fun toString(): String {
        return StringBuilder()
            .append("|id: ")
            .append(this.id)
            .append("| weekday: ")
            .append(this.weekday)
            .append("| portion: ")
            .append(this.portion)
            .append("| recipe_id: ")
            .append(this.recipe_id)
            .append("|")
            .toString()
    }
}
