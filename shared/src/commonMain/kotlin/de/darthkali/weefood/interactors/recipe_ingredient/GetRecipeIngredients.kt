package de.darthkali.weefood.interactors.recipe_ingredient

import de.darthkali.weefood.datasource.database.queries.ingredient.IngredientQueries
import de.darthkali.weefood.datasource.database.queries.recipeIngredient.RecipeIngredientQueries
import de.darthkali.weefood.domain.model.Ingredient
import de.darthkali.weefood.util.Logger
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetRecipeIngredients : KoinComponent {

    private val recipeIngredientQueries: RecipeIngredientQueries by inject()
    private val ingredientQueries: IngredientQueries by inject()
    private val logger = Logger("GetRecipeIngredients")

    /**
     * search ingredient by AppId
     * is there a result, then return the database-id
     * is the result == null, then insert new ingredient and return the new database-id
     */
    fun execute(recipeId: Int): List<Ingredient>? {
        return try {
            val ingredientResultList: MutableList<Ingredient> = mutableListOf()
            val recipeIngredients =
                recipeIngredientQueries.getAllRecipeIngredientByRecipeId(recipeId)
            for (recipeIngredient in recipeIngredients) {
                val ingredient = ingredientQueries.getIngredientById(recipeIngredient.ingredient_id)
                if (ingredient != null) {
                    val ingredientResult = Ingredient(
                        internalId = recipeIngredient.ingredient_id,
                        name = ingredient.name,
                        image = ingredient.image,
                        apiId = ingredient.apiId,
                        quantity = recipeIngredient.quantity,
                        unit = recipeIngredient.unit
                    )
                    ingredientResultList.add(ingredientResult)
                } else {
                    logger.log("Ingredient mit der IngredientId ${recipeIngredient.ingredient_id} konnte nicht gefunden werden")
                }
            }
            ingredientResultList
        } catch (e: Exception) {
            logger.log(e.toString())
            return null
        }
    }
}