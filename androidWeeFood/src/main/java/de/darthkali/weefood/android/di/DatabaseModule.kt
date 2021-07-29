package de.darthkali.weefood.android.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import de.darthkali.weefood.BaseApplication
import de.darthkali.weefood.datasource.database.*
import de.darthkali.weefood.datasource.database.ingredient.IngredientDb
import de.darthkali.weefood.datasource.database.ingredient.IngredientDbImpl
import de.darthkali.weefood.domain.util.DatetimeUtil
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideWeeFoodDatabase(context: BaseApplication): WeeFoodDatabase {
        return WeeFoodDatabaseFactory(driverFactory = DriverFactory(context)).createDatabase()
    }

    @Singleton
    @Provides
    fun provideIngredientDb(
        weeFoodDatabase: WeeFoodDatabase
    ): IngredientDb {
        return IngredientDbImpl(
            weeFoodDatabase = weeFoodDatabase
        )
    }


    @Singleton
    @Provides
    fun provideRecipeCache(
        weeFoodDatabase: WeeFoodDatabase,
        datetimeUtil: DatetimeUtil,
    ): RecipeCache {
        return RecipeCacheImpl(
            weeFoodDatabase = weeFoodDatabase,
            datetimeUtil = datetimeUtil,
        )
    }


}