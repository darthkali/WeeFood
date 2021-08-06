package de.darthkali.weefood.datasource.network


import de.darthkali.weefood.datasource.network.mapper.IngredientListMapper
import de.darthkali.weefood.datasource.network.model.IngredientSearchResponse
import de.darthkali.weefood.domain.model.Ingredient
import io.ktor.client.request.get
import io.ktor.client.request.url
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class IngredientServiceImpl : IngredientService, KoinComponent {

    private val ktorClientFactory: KtorClientFactory by inject()
    private val httpClient = ktorClientFactory.build()
    private val mapper = IngredientListMapper()

    override suspend fun searchIngredient(query: String, page: Int): List<Ingredient> {

        val offset: Int = (page - 1) * PAGINATION_PAGE_SIZE

        return mapper.mapTo(
            httpClient.get<IngredientSearchResponse> {
                url("$BASE_URL/food/ingredients/search?apiKey=$API_KEY&query=$query&metaInformation=true&offset=$offset&number=$PAGINATION_PAGE_SIZE")
            }.results
        )
    }

    companion object {
        const val API_KEY = "a18522db266047e4b92632a17b82bd6f"
        const val BASE_URL = "https://api.spoonacular.com"
        const val PAGINATION_PAGE_SIZE = 30
        const val IMAGE_URL_SMALL = "https://spoonacular.com/cdn/ingredients_100x100"
        const val IMAGE_URL_MEDIUM = "https://spoonacular.com/cdn/ingredients_250x250"
        const val IMAGE_URL_LARGE = "https://spoonacular.com/cdn/ingredients_500x500"
        const val NO_IMAGE = "no.jpg"
    }
}






