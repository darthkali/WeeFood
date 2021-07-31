package de.darthkali.weefood.di

import de.darthkali.weefood.datasource.database.*
import de.darthkali.weefood.datasource.database.ingredient.IngredientDb
import de.darthkali.weefood.datasource.database.ingredient.IngredientDbImpl
import de.darthkali.weefood.domain.util.DatetimeUtil

class DatabaseModule {

    private val driverFactory: DriverFactory by lazy {DriverFactory()}
    val weeFoodDatabase: WeeFoodDatabase by lazy{
        WeeFoodDatabaseFactory(driverFactory = driverFactory).createDatabase()
    }



    val ingredientDb: IngredientDb by lazy {
        IngredientDbImpl(
            weeFoodDatabase = weeFoodDatabase,
        )
    }

    val recipeCache: RecipeCache by lazy {
        RecipeCacheImpl(
            weeFoodDatabase = weeFoodDatabase,
            datetimeUtil = DatetimeUtil()
        )
    }


}