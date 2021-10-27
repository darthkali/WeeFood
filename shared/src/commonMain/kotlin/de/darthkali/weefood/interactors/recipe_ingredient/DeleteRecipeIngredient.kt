package de.darthkali.weefood.interactors.recipe_ingredient

import de.darthkali.weefood.datasource.database.repository.recipeIngredient.RecipeIngredientRepository
import de.darthkali.weefood.util.Logger
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class DeleteRecipeIngredient : KoinComponent {

    private val recipeIngredientRepository: RecipeIngredientRepository by inject()
    private val logger = Logger("DeleteRecipeIngredient")

    /**
     * @param recipeId: Int
     * @param ingredientId: Int
     *
     * delete the entry in the recipeIngredients database table
     * depends on the recipeId and ingredientId
     *
     * @return boolean
     */
    fun execute(recipeId: Int, ingredientId: Int): Boolean {
        return try {
            recipeIngredientRepository.deleteRecipeIngredientByRecipeIdAndIngredientId(
                recipeId,
                ingredientId
            )
            true
        } catch (e: Exception) {
            logger.log(e.toString())
            false
        }
    }
}
