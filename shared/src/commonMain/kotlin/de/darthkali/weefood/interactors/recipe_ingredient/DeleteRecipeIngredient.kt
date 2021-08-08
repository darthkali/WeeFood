package de.darthkali.weefood.interactors.recipe_ingredient

import de.darthkali.weefood.datasource.database.model.RecipeIngredientDb
import de.darthkali.weefood.datasource.database.queries.recipeIngredient.RecipeIngredientQueries
import de.darthkali.weefood.domain.model.Ingredient
import de.darthkali.weefood.util.Logger
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class DeleteRecipeIngredient : KoinComponent {

    private val recipeIngredientQueries: RecipeIngredientQueries by inject()
    private val logger = Logger("DeleteRecipeIngredient")

    /**
     * search ingredient by AppId
     * is there a result, then return the database-id
     * is the result == null, then insert new ingredient and return the new database-id
     */
    fun execute(recipeId: Int, ingredientId: Int): Boolean {
        return try {
            recipeIngredientQueries.deleteRecipeIngredientByRecipeIdAndIngredientId(recipeId, ingredientId)
            true
        } catch (e: Exception) {
            logger.log(e.toString())
            false
        }
    }
}