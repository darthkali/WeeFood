package de.darthkali.weefood.domain.model

data class Recipe(
    val id            : Int,
    val name          : String,
    val image         : String? = "",
    val cooking_time  : Int,
    val unit          : String,
    val description   : String? = ""
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
            .append(this.unit)
            .append("| description: ")
            .append(this.description)
            .append("|")
            .toString()
    }
}
