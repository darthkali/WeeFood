package de.darthkali.weefood.interactors.recipe_ingredient

import de.darthkali.weefood.datasource.database.queries.ingredient.IngredientQueries
import de.darthkali.weefood.datasource.database.queries.recipeIngredient.RecipeIngredientQueries
import de.darthkali.weefood.domain.model.Ingredient
import de.darthkali.weefood.util.Logger
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetIngredientsFromRecipe : KoinComponent {

    private val recipeIngredientQueries: RecipeIngredientQueries by inject()
    private val ingredientQueries: IngredientQueries by inject()
    private val logger = Logger("GetRecipeIngredients")

    /**
     * @param recipeId: Int
     *
     * search all ingredients for the recipe in database
     * create for each a ingredient domain model
     *
     * @return ingredientResultList
     */
    fun execute(recipeId: Int): List<Ingredient>? {
        return try {
            val ingredientResultList: MutableList<Ingredient> = mutableListOf()

            recipeIngredientQueries.getAllRecipeIngredientByRecipeId(recipeId)
                .forEach { recipeIngredient ->
                    ingredientQueries.getIngredientById(recipeIngredient.ingredient_id)?.let {
                        ingredientResultList.add(
                            Ingredient(
                                internalId = recipeIngredient.ingredient_id,
                                name = it.name,
                                image = it.image,
                                apiId = it.apiId,
                                quantity = recipeIngredient.quantity,
                                unit = recipeIngredient.unit
                            )
                        )
                    } ?: run {
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