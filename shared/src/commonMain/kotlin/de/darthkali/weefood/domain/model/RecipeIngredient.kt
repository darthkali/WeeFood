package de.darthkali.weefood.domain.model

data class RecipeIngredient(
    val id              : Int = 0,
    val quantity        : Float,
    val unit            : String,
    val recipe_id       : Int,
    val ingredient_id   : Int,
){
    override fun toString(): String {
        return StringBuilder()
            .append("|id: ")
            .append(this.id)
            .append("| quantity: ")
            .append(this.quantity)
            .append("| unit: ")
            .append(this.unit)
            .append("| recipe_id: ")
            .append(this.recipe_id)
            .append("| ingredient_id: ")
            .append(this.ingredient_id)
            .append("|")
            .toString()
    }
}
