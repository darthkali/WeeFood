package de.darthkali.weefood.datasource.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecipeSearchResponse(

    @SerialName("count")
    var count: Int,

    @SerialName("results")
    var results: List<RecipeDto>,
)