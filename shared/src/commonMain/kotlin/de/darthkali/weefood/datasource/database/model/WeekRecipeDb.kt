package de.darthkali.weefood.datasource.database.model

import de.darthkali.weefood.domain.util.enums.Weekday

data class WeekRecipeDb(
    val id: Int = 0,
    val weekday: Weekday,
    val portion: Int,
    val recipe_id: Int,
) {
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

    /**
     * to check that 2 WeekRecipes are the same
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
        return ((other is WeekRecipeDb)
                && other.weekday == this.weekday
                && other.portion == this.portion)
                && other.recipe_id == this.recipe_id
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
