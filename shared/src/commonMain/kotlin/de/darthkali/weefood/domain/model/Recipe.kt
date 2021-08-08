package de.darthkali.weefood.domain.model

data class Recipe(
    var databaseId: Int? = null,
    var name: String,
    var image: String? = "",
    var cooking_time: Int? = 0,
    var cooking_time_unit: String,
    var description: String? = "",
    var portion: Int? = 0,
    var ingredients: List<Ingredient>
) {
//    override fun toString(): String {
//        return StringBuilder()
//            .append("|id: ")
//            .append(this.id)
//            .append("| name: ")
//            .append(this.name)
//            .append("| image: ")
//            .append(this.image)
//            .append("| cooking_time: ")
//            .append(this.cooking_time)
//            .append("| unit: ")
//            .append(this.cooking_time_unit)
//            .append("| description: ")
//            .append(this.description)
//            .append("|")
//            .toString()
//    }
//
    /**
     * to check that 2 Recipes are the same
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


        // if we will only get a recipe without a portion, we will set the portion equal to the other portions
        if (this.portion == 0 && (other is Recipe)) {
            this.portion = other.portion
        }


        return ((other is Recipe)
                && other.name == this.name
                && other.image == this.image)
                && other.cooking_time == this.cooking_time
                && other.cooking_time_unit == this.cooking_time_unit
                && other.description == this.description
//                && other.portion == this.portion
//                && other.ingredients.hashCode() == this.ingredients.hashCode()
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
