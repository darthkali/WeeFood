package de.darthkali.weefood.datasource.network

import de.darthkali.weefood.datasource.network.model.IngredientSearchResponse
import de.darthkali.weefood.domain.model.Recipe
import io.ktor.client.*
import io.ktor.client.request.*

class IngredientServiceImpl(
    private val httpClient: HttpClient,
) : IngredientService {


    override suspend fun search(query: String, page: Int): List<Recipe> {

        val offset: Int  = (page - 1)  * RECIPE_PAGINATION_PAGE_SIZE + 1

        return httpClient.get<IngredientSearchResponse> {
            url("$BASE_URL/food/ingredients/search?apiKey=$API_KEY&query=$query&metaInformation=true&offset=$offset&number=$RECIPE_PAGINATION_PAGE_SIZE")
        }.results.toRecipeList()
    }

//    override suspend fun get(id: Int): Recipe {
//        return httpClient.get<RecipeDto> {
//            url("$BASE_URL/get?id=$id")
//            header("Authorization", API_KEY)
//        }.toRecipe()
//    }


//    companion object {
//        const val TOKEN = "Token 9c8b06d329136da358c2d00e76946b0111ce2c48"
//        const val BASE_URL = "https://food2fork.ca/api/recipe"
//        const val RECIPE_PAGINATION_PAGE_SIZE = 30
//    }

    companion object {
        const val API_KEY = "a18522db266047e4b92632a17b82bd6f"
        const val BASE_URL = "https://api.spoonacular.com"
        const val RECIPE_PAGINATION_PAGE_SIZE = 30
    }
}






