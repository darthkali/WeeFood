package de.darthkali.weefood.interactors.ingredient_list

import de.darthkali.weefood.datasource.database.queries.ingredient.IngredientQueries
import de.darthkali.weefood.datasource.database.mapper.ingredient.IngredientMapper
import de.darthkali.weefood.domain.model.Ingredient
import de.darthkali.weefood.util.Logger
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SaveIngredient : KoinComponent {

    private val ingredientQueries: IngredientQueries by inject()
    private val logger = Logger("SaveIngredient")
    private val mapper = IngredientMapper()

    /**
     * search ingredient by AppId
     * is there a result, then return the database-id
     * is the result == null, then insert new ingredient and return the new database-id
     */
    fun execute(ingredient: Ingredient): Int? {
        return try {
            ingredientQueries.getIngredientByApiId(ingredient.apiId).let {
                if (it != null) {
                    ingredientQueries.updateIngredient(mapper.mapBack(ingredient))
                } else {
                    ingredientQueries.insertIngredient(mapper.mapBack(ingredient))
                }
            }
        } catch (e: Exception) {
            logger.log(e.toString())
            return null
        }
    }
}
