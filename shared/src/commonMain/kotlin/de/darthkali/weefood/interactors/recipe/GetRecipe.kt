package de.darthkali.weefood.interactors.recipe

import de.darthkali.weefood.datasource.database.repository.recipe.RecipeRepository
import de.darthkali.weefood.domain.model.Recipe
import de.darthkali.weefood.interactors.recipe_ingredient.GetIngredientsFromRecipe
import de.darthkali.weefood.util.Logger
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetRecipe : KoinComponent {

    private val recipeRepository: RecipeRepository by inject()
    private val getIngredientsFromRecipe: GetIngredientsFromRecipe by inject()
    private val logger = Logger("SaveRecipe")


    /**
     * @param recipeId: Int
     *
     * check if we have a valid recipeId?
     *
     * is the response not null, then create a Recipe and return it
     * else place a log message and return null
     *
     * @return Recipe
     */
    fun execute(recipeId: Int): Recipe? {
        return try {
            if (recipeId > 0) {
                recipeRepository.getRecipeById(recipeId)?.let {
                    return Recipe(
                        databaseId = it.id,
                        name = it.name,
                        image = it.image,
                        cooking_time = it.cooking_time,
                        cooking_time_unit = it.cooking_time_unit,
                        recipeDescription = it.description,
                        ingredients = getIngredientsFromRecipe.execute(it.id)!!,
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
