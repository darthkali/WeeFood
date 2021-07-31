package de.darthkali.weefood.datasource.database

import de.darthkali.weefood.datasource.network.IngredientServiceImpl.Companion.PAGINATION_PAGE_SIZE
import de.darthkali.weefood.domain.model.Ingredient
import de.darthkali.weefood.domain.util.DatetimeUtil

class RecipeCacheImpl(
    val weeFoodDatabase: WeeFoodDatabase,
    private val datetimeUtil: DatetimeUtil,
): RecipeCache {

    private var queries: IngredientDbQueries = weeFoodDatabase.ingredientDbQueries

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
        return queries.searchIngredients(
            query = query,
            pageSize = PAGINATION_PAGE_SIZE.toLong(),
            offset = ((page - 1)* PAGINATION_PAGE_SIZE).toLong()
        ).executeAsList().toIngredientList()
    }

    override fun getAll(page: Int): List<Ingredient> {
        return queries.getAllIngredients(
            pageSize = PAGINATION_PAGE_SIZE.toLong(),
            offset = ((page - 1) * PAGINATION_PAGE_SIZE).toLong()
        ).executeAsList().toIngredientList()
    }

    override fun get(recipeId: Int): Ingredient? {
        return try {
            queries.getIngredientById(id = recipeId.toLong()).executeAsOne().toIngredient()
        }catch (e: NullPointerException){
            null
        }
    }
}