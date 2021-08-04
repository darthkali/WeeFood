package de.darthkali.weefood.datasource.database.mapper.recipe

import de.darthkali.weefood.datasource.BaseMapper
import de.darthkali.weefood.datasource.database.model.IngredientDb
import de.darthkali.weefood.datasource.database.model.RecipeDb
import de.darthkali.weefood.domain.model.Ingredient
import de.darthkali.weefood.domain.model.Recipe

class RecipeMapper : BaseMapper<Recipe, RecipeDb> {

    override fun mapTo(db: RecipeDb): Recipe {
        return Recipe(
            internalId = db.id,
            name = db.name,
            image = db.image,
            cooking_time = db.cooking_time,
            cooking_time_unit = db.cooking_time_unit,
            description = db.description,
            portion = 0,
            ingredients = listOf(),
        )
    }

    override fun mapBack(internal: Recipe): RecipeDb {
        return RecipeDb(
            id = internal.internalId ?: 0,
            name = internal.name,
            image = internal.image,
            cooking_time = internal.cooking_time ?: 0,
            cooking_time_unit = internal.cooking_time_unit,
            description = internal.description,
        )
    }
}