package de.darthkali.weefood.domain.model

import kotlinx.serialization.SerialName

data class Recipe (
    val id: Int,
    val name: String,
    val image: String,
    val aisle: String,
    val possibleUnits: List<String> = listOf(),
)


