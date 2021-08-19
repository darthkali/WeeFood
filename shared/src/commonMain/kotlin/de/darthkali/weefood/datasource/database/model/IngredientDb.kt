package de.darthkali.weefood.datasource.database.model

data class IngredientDb(
    val id: Int = 0,
    val name: String? = "",
    val image: String? = "",
    val apiId: Int
) {
    override fun toString(): String {

        return StringBuilder()
            .append("|id: ")
            .append(this.id)
            .append("| name: ")
            .append(this.name)
            .append("| image: ")
            .append(this.image)
            .append("|")
            .append("| apiId: ")
            .append(this.apiId)
            .append("|")
            .toString()
    }

    /**
     * to check that 2 Ingredients are the same
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
        return ((other is IngredientDb)
                && other.name == this.name
                && other.image == this.image)
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
