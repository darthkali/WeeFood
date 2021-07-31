package de.darthkali.weefood.di

import de.darthkali.weefood.datasource.network.KtorClientFactory
import de.darthkali.weefood.datasource.network.IngredientService
import de.darthkali.weefood.datasource.network.IngredientServiceImpl

//class NetworkModule {
//
//    val ingredientService: IngredientService by lazy {
//        IngredientServiceImpl(
//            httpClient = KtorClientFactory().build(),
//        )
//    }
//
//}