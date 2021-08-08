package de.darthkali.weefood.interactors.recipe

import de.darthkali.weefood.datasource.database.queries.ingredient.IngredientQueries
import de.darthkali.weefood.datasource.database.queries.recipe.RecipeQueries
import de.darthkali.weefood.datasource.database.queries.recipeIngredient.RecipeIngredientQueries
import de.darthkali.weefood.domain.model.Recipe
import de.darthkali.weefood.interactors.recipe_ingredient.GetRecipeIngredients
import de.darthkali.weefood.util.Logger
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetRecipe : KoinComponent {

    private val recipeQueries: RecipeQueries by inject()
    private val ingredientQueries: IngredientQueries by inject()
    private val recipeIngredientQueries: RecipeIngredientQueries by inject()
    private val getRecipeIngredients: GetRecipeIngredients by inject()
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
                recipeQueries.getRecipeById(recipeId)?.let{
                    return Recipe(
                        databaseId = it.id,
                        name = it.name,
                        image = it.image,
                        cooking_time = it.cooking_time,
                        cooking_time_unit = it.cooking_time_unit,
                        description = it.description,
                        ingredients = getRecipeIngredients.execute(it.id)!!,
                    )
                }
            }
            logger.log("Rezept mit der RecipeId $recipeId konnte nicht gefunden werden")
            null
        } catch (e: Exception) {
            logger.log(e.toString())
            null
        }
    }
}
