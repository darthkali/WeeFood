package de.darthkali.weefood.di

import de.darthkali.weefood.datasource.database.*
import de.darthkali.weefood.datasource.database.ingredient.IngredientDb
import de.darthkali.weefood.datasource.database.ingredient.IngredientDbImpl
import de.darthkali.weefood.domain.util.DatetimeUtil
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

//class DatabaseModule: KoinComponent {

//    private val driverFactory: DriverFactory by lazy {DriverFactory()}
//    private val driverFactory: DriverFactory by inject()

//    val weeFoodDatabase: WeeFoodDatabase by lazy{
//        WeeFoodDatabaseFactory(driverFactory = driverFactory).createDatabase()
//    }

//    val weeFoodDatabase: WeeFoodDatabase by lazy{
//        WeeFoodDatabaseFactory().createDatabase()
//    }
//
//
//
//    val ingredientDb: IngredientDb by lazy {
//        IngredientDbImpl(
//            weeFoodDatabase = weeFoodDatabase,
//        )
//    }
//
//    val recipeCache: RecipeCache by lazy {
//        RecipeCacheImpl(
//            weeFoodDatabase = weeFoodDatabase,
//            datetimeUtil = DatetimeUtil()
//        )
//    }


//}