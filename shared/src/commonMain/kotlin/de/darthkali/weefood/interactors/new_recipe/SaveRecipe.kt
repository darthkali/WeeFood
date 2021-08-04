package de.darthkali.weefood.interactors.new_recipe

import de.darthkali.weefood.datasource.database.queries.ingredient.IngredientQueries
import de.darthkali.weefood.datasource.database.mapper.ingredient.IngredientMapper
import de.darthkali.weefood.datasource.database.mapper.recipe.RecipeMapper
import de.darthkali.weefood.datasource.database.model.RecipeIngredientDb
import de.darthkali.weefood.datasource.database.queries.recipe.RecipeQueries
import de.darthkali.weefood.datasource.database.queries.recipeIngredient.RecipeIngredientQueries
import de.darthkali.weefood.domain.model.Ingredient
import de.darthkali.weefood.domain.model.Recipe
import de.darthkali.weefood.interactors.ingredient_list.SaveIngredient
import de.darthkali.weefood.util.Logger
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SaveRecipe : KoinComponent {

    private val recipeQueries: RecipeQueries by inject()
    private val recipeIngredientQueries: RecipeIngredientQueries by inject()
    private val saveIngredient: SaveIngredient by inject()
    private val saveRecipeIngredient: SaveRecipeIngredient by inject()
    private val logger = Logger("SaveRecipe")
    private val mapper = RecipeMapper()


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
    fun execute(recipe: Recipe): Int? {
        return try {

            val recipeId = if (recipe.internalId == null) {
                recipeQueries.insertRecipe(mapper.mapBack(recipe))!!
            } else {
                recipeQueries.updateRecipe(mapper.mapBack(recipe))!!
            }

            recipe.ingredients.forEach {
                val ingredientId = saveIngredient.execute(it)!!
                val recipeIngredientDb = RecipeIngredientDb(
                    quantity = it.quantity,
                    unit = it.unit,
                    recipe_id = recipeId,
                    ingredient_id = ingredientId
                )
                saveRecipeIngredient.execute(recipeIngredientDb)
            }
            recipeId
        } catch (e: Exception) {
            logger.log(e.toString())
            return null
        }
    }
}
