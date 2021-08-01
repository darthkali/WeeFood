package de.darthkali.weefood.android.di

import de.darthkali.weefood.android.presentation.screens.ingredient_list.IngredientListViewModel
import de.darthkali.weefood.android.presentation.screens.recipe_list.RecipeListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

//@Module
//@InstallIn(SingletonComponent::class)
//object AppModule {
//
//    @Singleton
//    @Provides
//    fun provideApplication(@ApplicationContext app: Context): BaseApplication {
//        return app as BaseApplication
//    }
//
//    @Singleton
//    @Provides
//    fun provideDateUtil(): DatetimeUtil {
//        return DatetimeUtil()
//    }
//}

    val appModule = module {
        viewModel { IngredientListViewModel() }
        viewModel { RecipeListViewModel(get()) }
    }
