package de.darthkali.weefood.interactors.new_recipe

import de.darthkali.weefood.datasource.database.queries.ingredient.IngredientQueries
import de.darthkali.weefood.datasource.database.queries.recipe.RecipeQueries
import de.darthkali.weefood.datasource.database.queries.recipeIngredient.RecipeIngredientQueries
import de.darthkali.weefood.domain.model.Ingredient
import de.darthkali.weefood.domain.model.Recipe
import de.darthkali.weefood.util.Logger
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetRecipe : KoinComponent {

    private val recipeQueries: RecipeQueries by inject()
    private val ingredientQueries: IngredientQueries by inject()
    private val recipeIngredientQueries: RecipeIngredientQueries by inject()
    private val logger = Logger("SaveRecipe")


    /**
     * exits the recipe already?
     *
     * then update recipe in database
     * update recipeIngredients in database
     *
     * else insert a new recipe to database
     * insert recipeIngredients to database
     *
     */
    fun execute(recipeId: Int): Recipe? {
        return try {
            if(recipeId > 0){
                val recipeDbResult = recipeQueries.getRecipeById(recipeId)!!
                val recipeIngredientDbResult: MutableList<Ingredient> = mutableListOf()

                recipeIngredientQueries.getAllRecipeIngredientByRecipeId(recipeDbResult.id).forEach {

                    val ingredientDbResult = ingredientQueries.getIngredientById(it.ingredient_id)!!
                    val ingredientResult = Ingredient(
                        name = ingredientDbResult.name,
                        image = ingredientDbResult.image,
                        apiId = ingredientDbResult.apiId,
                        quantity = it.quantity,
                        unit = it.unit
                    )
                    recipeIngredientDbResult.add(ingredientResult)
                }

                return Recipe(
                    internalId = recipeDbResult.id,
                    name = recipeDbResult.name,
                    image = recipeDbResult.image,
                    cooking_time = recipeDbResult.cooking_time,
                    cooking_time_unit = recipeDbResult.cooking_time_unit,
                    description = recipeDbResult.description,
                    ingredients = recipeIngredientDbResult,
                )
            }
            null
        } catch (e: Exception) {
            logger.log(e.toString())
            return null
        }
    }
}
