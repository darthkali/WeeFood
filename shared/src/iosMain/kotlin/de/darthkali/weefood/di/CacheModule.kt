package de.darthkali.weefood.di

import de.darthkali.weefood.datasource.database.*
import de.darthkali.weefood.domain.util.DatetimeUtil

class CacheModule {

    private val driverFactory: DriverFactory by lazy {DriverFactory()}
    val weeFoodDatabase: WeeFoodDatabase by lazy{
        WeeFoodDatabaseFactory(driverFactory = driverFactory).createDatabase()
    }
    val recipeCache: RecipeCache by lazy {
        RecipeCacheImpl(
            weeFoodDatabase = weeFoodDatabase,
            datetimeUtil = DatetimeUtil()
        )
    }


}