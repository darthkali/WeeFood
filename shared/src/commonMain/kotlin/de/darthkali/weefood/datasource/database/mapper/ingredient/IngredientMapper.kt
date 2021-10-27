package de.darthkali.weefood.datasource.database.mapper.ingredient

import de.darthkali.weefood.datasource.BaseMapper
import de.darthkali.weefood.datasource.database.model.IngredientDb
import de.darthkali.weefood.domain.model.Ingredient

class IngredientMapper : BaseMapper<Ingredient, IngredientDb> {

    override fun mapTo(db: IngredientDb): Ingredient {
        return Ingredient(
            apiId = db.apiId,
            name = db.name,
            image = db.image,
            quantity = 0F, // TODO: korrekter Wert?
            unit = "", // TODO: korrekter Wert?
        )
    }

    override fun mapBack(internal: Ingredient): IngredientDb {
        return IngredientDb(
            id = 0,
            apiId = internal.apiId,
            name = internal.name,
            image = internal.image,
        )
    }
}
