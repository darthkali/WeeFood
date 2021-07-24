package de.darthkali.weefood.datasource.cache

import de.darthkali.weefood.datasource.network.IngredientServiceImpl.Companion.RECIPE_PAGINATION_PAGE_SIZE
import de.darthkali.weefood.domain.model.Ingredient
import de.darthkali.weefood.domain.util.DatetimeUtil

class RecipeCacheImpl(
    val recipeDatabase: RecipeDatabase,
    private val datetimeUtil: DatetimeUtil,
): RecipeCache {

    private var queries: RecipeDbQueries = recipeDatabase.recipeDbQueries

    override fun insert(ingredient: Ingredient) {
       // queries.insertRecipe(
         //   id = recipe.id.toLong()
            //TODO add cache
//            name = recipe.name,
//            image = recipe.image,
//            aisle = recipe.aisle,
//            possibleUnits = recipe.possibleUnits.convertIngredientListToString(),
        //)
    }

    override fun insert(ingredients: List<Ingredient>) {
        for(recipe in ingredients){
            insert(recipe)
        }
    }

    override fun search(query: String, page: Int): List<Ingredient> {
        return queries.searchRecipes(
            query = query,
            pageSize = RECIPE_PAGINATION_PAGE_SIZE.toLong(),
            offset = ((page - 1)* RECIPE_PAGINATION_PAGE_SIZE).toLong()
        ).executeAsList().toRecipeList()
    }

    override fun getAll(page: Int): List<Ingredient> {
        return queries.getAllRecipes(
            pageSize = RECIPE_PAGINATION_PAGE_SIZE.toLong(),
            offset = ((page - 1) * RECIPE_PAGINATION_PAGE_SIZE).toLong()
        ).executeAsList().toRecipeList()
    }

    override fun get(recipeId: Int): Ingredient? {
        return try {
            queries.getRecipeById(id = recipeId.toLong()).executeAsOne().toRecipe()
        }catch (e: NullPointerException){
            null
        }
    }
}