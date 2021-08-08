package de.darthkali.weefood.interactors.recipe_ingredient


import de.darthkali.weefood.datasource.database.model.RecipeIngredientDb
import de.darthkali.weefood.datasource.database.queries.recipeIngredient.RecipeIngredientQueries
import de.darthkali.weefood.util.Logger
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SaveRecipeIngredient : KoinComponent {

    private val recipeIngredientQueries: RecipeIngredientQueries by inject()
    private val logger = Logger("SaveRecipeIngredient")

    /**
     * search ingredient by AppId
     * is there a result, then return the database-id
     * is the result == null, then insert new ingredient and return the new database-id
     */
    fun execute(recipeIngredient: RecipeIngredientDb): Int? {
        return try {
            recipeIngredientQueries.getAllRecipeIngredientByRecipeId(recipeIngredient.recipe_id)
                .forEach {
                    if (it.ingredient_id == recipeIngredient.ingredient_id) {
                        return recipeIngredientQueries.updateRecipeIngredient(recipeIngredient)
                    }
                }
            recipeIngredientQueries.insertRecipeIngredient(recipeIngredient)
        } catch (e: Exception) {
            logger.log(e.toString())
            return null
        }
    }
}

