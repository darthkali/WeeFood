package de.darthkali.weefood.datasource.network

import de.darthkali.weefood.datasource.network.model.IngredientSearchResponse
import de.darthkali.weefood.domain.model.Ingredient
import io.ktor.client.*
import io.ktor.client.request.*

class IngredientServiceImpl(
    private val httpClient: HttpClient,
) : IngredientService {


    override suspend fun search(query: String, page: Int): List<Ingredient> {

        val offset: Int  = (page - 1)  * PAGINATION_PAGE_SIZE

        return httpClient.get<IngredientSearchResponse> {
            url("$BASE_URL/food/ingredients/search?apiKey=$API_KEY&query=$query&metaInformation=true&offset=$offset&number=$PAGINATION_PAGE_SIZE")
        }.results.toIngredientList()
    }

    companion object {
        const val API_KEY = "a18522db266047e4b92632a17b82bd6f"
        const val BASE_URL = "https://api.spoonacular.com"
        const val PAGINATION_PAGE_SIZE = 5
        const val IMAGE_URL_SMALL = "https://spoonacular.com/cdn/ingredients_100x100"
        const val IMAGE_URL_MEDIUM = "https://spoonacular.com/cdn/ingredients_250x250"
        const val IMAGE_URL_LARGE = "https://spoonacular.com/cdn/ingredients_500x500"
        const val NO_IMAGE = "no.jpg"
    }
}






