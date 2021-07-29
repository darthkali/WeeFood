package de.darthkali.weefood.domain.model

data class WeekRecipe(
    val id          : Int,
    val weekday     : Int,
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
