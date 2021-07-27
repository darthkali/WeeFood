package de.darthkali.weefood.android.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import de.darthkali.weefood.BaseApplication
import de.darthkali.weefood.datasource.database.*
import de.darthkali.weefood.domain.util.DatetimeUtil
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CacheModule {

    @Singleton
    @Provides
    fun provideRecipeDatabase(context: BaseApplication): WeeFoodDatabase {
        return RecipeDatabaseFactory(driverFactory = DriverFactory(context)).createDatabase()
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