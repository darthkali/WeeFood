package de.darthkali.weefood.datasource.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class IngredientDto(

    @SerialName("id")
    val id: Int,

    @SerialName("name")
    val name: String,

    @SerialName("image")
    val image: String,

    @SerialName("aisle")
    val aisle: String,

    @SerialName("possibleUnits")
    val possibleUnits: List<String> = emptyList(),

)