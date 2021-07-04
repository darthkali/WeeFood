package de.darthkali.weefood.datasource.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class RecipeDTO(

    @SerialName("pk")
    val pk: Int,

    @SerialName("title")
    val title: String,

    @SerialName("publisher")
    val publisher: String,

    @SerialName("featured_image")
    val featuredImage: String,

    @SerialName("rating")
    val rating: Int,

    @SerialName("source_url")
    val sourceUrl: String,

    @SerialName("ingredients")
    val ingredients: List<String> = listOf(),

    @SerialName("long_date_added")
    val dateAdded: Long,

    @SerialName("long_date_updated")
    val dateUpdated: Long,


)