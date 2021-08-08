package de.darthkali.weefood.datasource.network.mapper

import de.darthkali.weefood.datasource.BaseMapper
import de.darthkali.weefood.datasource.network.model.IngredientDto
import de.darthkali.weefood.domain.model.Ingredient

class IngredientMapper : BaseMapper<Ingredient, IngredientDto> {

    override fun mapTo(db: IngredientDto): Ingredient {
        return Ingredient(
            apiId = db.apiId,
            name = db.name,
            image = db.image,
            quantity = 0F,
            unit = "",
        )
    }

    override fun mapBack(internal: Ingredient): IngredientDto {
        return IngredientDto(
            apiId = internal.apiId,
            name = internal.name,
            image = internal.image,
        )
    }
}