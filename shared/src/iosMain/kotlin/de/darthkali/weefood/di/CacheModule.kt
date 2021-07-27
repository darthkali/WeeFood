package de.darthkali.weefood.di

import de.darthkali.weefood.datasource.database.*
import de.darthkali.weefood.domain.util.DatetimeUtil

class CacheModule {

    private val driverFactory: DriverFactory by lazy {DriverFactory()}
    val recipeDatabase: RecipeDatabase by lazy{
        RecipeDatabaseFactory(driverFactory = driverFactory).createDatabase()
    }
    val recipeCache: RecipeCache by lazy {
        RecipeCacheImpl(
            recipeDatabase = recipeDatabase,
            datetimeUtil = DatetimeUtil()
        )
    }


}