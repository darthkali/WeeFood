package de.darthkali.weefood.domain.model

data class Ingredient (
    val id      : Int = 0,
    val name    : String? = "",
    val image   : String? = "",
    // TODO:  APi ID
){
    override fun toString(): String {
        return StringBuilder()
            .append("|id: ")
            .append(this.id)
            .append("| name: ")
            .append(this.name)
            .append("| image: ")
            .append(this.image)
            .append("|")
            .toString()
    }
}

