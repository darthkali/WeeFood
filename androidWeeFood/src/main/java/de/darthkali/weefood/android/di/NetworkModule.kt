package de.darthkali.weefood.android.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import de.darthkali.weefood.datasource.network.KtorClientFactory
import de.darthkali.weefood.datasource.network.IngredientService
import de.darthkali.weefood.datasource.network.IngredientServiceImpl
import io.ktor.client.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule{

    @Singleton
    @Provides
    fun provideHttpClient(): HttpClient{
        return KtorClientFactory().build()
    }

    @Singleton
    @Provides
    fun provideIngredientService(
        httpClient: HttpClient,
    ): IngredientService {
        return IngredientServiceImpl(
            httpClient = httpClient,
        )
    }
}