package de.darthkali.weefood.domain.model

data class RecipeIngredient(
    val id: Int = 0,
    val quantity: Float,
    val unit: String,
    val recipe_id: Int,
    val ingredient_id: Int,
) {
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

    /**
     * to check that 2 RecipeIngredients are the same
     * without the id
     * this is important for the tests because when we run the tests, we don't know
     * which ids the inserted items will get.
     *
     * with this we can easily check that this has the same attributes
     *
     * @param other: the other element to compare with this
     * @return a boolean true / false
     */
    override fun equals(other: Any?): Boolean {
        return ((other is RecipeIngredient)
                && other.quantity == this.quantity
                && other.unit == this.unit)
                && other.recipe_id == this.recipe_id
                && other.ingredient_id == this.ingredient_id
    }

    /**
     * to check that 2 Ingredients are exact the same
     * it also included the id
     *
     * @return a hash value as an Int
     */
    override fun hashCode(): Int {
        return super.hashCode()
    }
}
