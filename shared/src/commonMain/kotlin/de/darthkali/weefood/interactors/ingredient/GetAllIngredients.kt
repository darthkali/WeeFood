package de.darthkali.weefood.interactors.ingredient

import de.darthkali.weefood.datasource.database.queries.ingredient.IngredientQueries
import de.darthkali.weefood.datasource.database.mapper.ingredient.IngredientListMapper
import de.darthkali.weefood.domain.model.Ingredient
import de.darthkali.weefood.util.Logger
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetAllIngredients : KoinComponent {

    private val ingredientQueries: IngredientQueries by inject()
    private val logger = Logger("GetAllIngredients")
    private val mapper = IngredientListMapper()

    fun execute(): List<Ingredient>{
        return mapper.mapTo(ingredientQueries.getAllIngredients())
    }

}

