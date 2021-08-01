package de.darthkali.weefood.android.di

//
//@Module
//@InstallIn(SingletonComponent::class)
//object InteractorModule {
//
//    @Singleton
//    @Provides
//    fun provideSearchIngredient(
//        ingredientService: IngredientService,
//    ): SearchIngredient {
//        return SearchIngredient(
//            ingredientService = ingredientService,
//        )
//    }
//
//    @InternalAPI
//    @Singleton
//    @Provides
//    fun provideGetRecipe(
//        recipeCache: RecipeCache,
//    ): GetRecipe {
//        return GetRecipe(
//            recipeCache = recipeCache
//        )
//    }
//
//    @InternalAPI
//    @Singleton
//    @Provides
//    fun provideSaveIngredient(
//        ingredientDb: IngredientDb,
//    ): SaveIngredient {
//        return SaveIngredient(
//            ingredientDb = ingredientDb
//        )
//    }
//
//    @InternalAPI
//    @Singleton
//    @Provides
//    fun provideGetAllIngredients(
//        ingredientDb: IngredientDb,
//    ): GetAllIngredients {
//        return GetAllIngredients(
//            ingredientDb = ingredientDb
//        )
//    }
//}