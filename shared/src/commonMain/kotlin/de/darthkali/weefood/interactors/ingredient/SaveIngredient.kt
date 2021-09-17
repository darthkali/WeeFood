package de.darthkali.weefood.interactors.ingredient

import de.darthkali.weefood.datasource.database.mapper.ingredient.IngredientMapper
import de.darthkali.weefood.datasource.database.model.RecipeIngredientDb
import de.darthkali.weefood.datasource.database.repository.ingredient.IngredientRepository
import de.darthkali.weefood.domain.model.Ingredient
import de.darthkali.weefood.interactors.recipe_ingredient.SaveRecipeIngredient
import de.darthkali.weefood.util.Logger
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SaveIngredient : KoinComponent {

    private val ingredientRepository: IngredientRepository by inject()
    private val saveRecipeIngredient: SaveRecipeIngredient by inject()
    private val logger = Logger("SaveIngredient")
    private val mapper = IngredientMapper()

    /**
     * @param ingredient: Ingredient
     * @param recipeId: Int
     *
     * search ingredient by ApiId
     * is there a result, then update the ingredient and return the database-id
     * is the result == null, then insert new ingredient and return the new database-id
     * create a new recipe_ingredient
     *
     * @return recipeIngredient-ID
     */
    fun execute(ingredient: Ingredient, recipeId: Int): Int? {
        return try {
            var ingredientId: Int
            ingredientRepository.getIngredientByApiId(ingredient.apiId).let {
                ingredientId = if (it != null) {
                    ingredientRepository.updateIngredientByApiId(mapper.mapBack(ingredient))!!
                } else {
                    ingredientRepository.insertIngredient(mapper.mapBack(ingredient))!!
                }

                val recipeIngredient = RecipeIngredientDb(
                    recipe_id = recipeId,
                    ingredient_id = ingredientId,
                    unit = "",
                    quantity = 0F,
                )
                saveRecipeIngredient.execute(recipeIngredient)
            }
        } catch (e: Exception) {
            logger.log(e.toString())
            return null
        }
    }
}
