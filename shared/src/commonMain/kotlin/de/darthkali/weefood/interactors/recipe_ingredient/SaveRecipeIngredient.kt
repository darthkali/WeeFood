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
     * @param recipeIngredient: RecipeIngredientDb
     *
     * search all ingredients for the recipe in database
     * iterates through the recipeIngredient-List
     * when the ingredient is the same, as the one we want to save
     * then make an update and return the database-id
     *
     * if no ingredient matches, then insert a ne recipeIngredient to the database
     * and return the database-id
     * @return recipeIngredient-ID
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

