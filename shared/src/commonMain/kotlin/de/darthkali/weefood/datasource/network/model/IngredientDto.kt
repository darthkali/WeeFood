package de.darthkali.weefood.datasource.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class IngredientDto(

    @SerialName("id")
    val apiId: Int,

    @SerialName("name")
    val name: String?,

    @SerialName("image")
    val image: String?,
)