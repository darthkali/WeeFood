//package de.darthkali.weefood.interactors.recipe_detail
//
//import de.darthkali.weefood.datasource.database.RecipeCache
//import de.darthkali.weefood.datasource.local.model.Ingredient
//import de.darthkali.weefood.domain.util.CommonFlow
//import de.darthkali.weefood.domain.util.DataState
//import de.darthkali.weefood.domain.util.asCommonFlow
//import de.darthkali.weefood.util.BuildConfig
//import de.darthkali.weefood.util.Logger
//import kotlinx.coroutines.delay
//import kotlinx.coroutines.flow.flow
//import org.koin.core.component.KoinComponent
//import org.koin.core.component.inject
//
///**
// * Retrieve a recipe from the cache given it's unique id.
// */
//class GetRecipe: KoinComponent{
//
//    private val recipeCache: RecipeCache by inject()
//    private val logger = Logger("SearchRecipes")
//
//    fun execute(
//        recipeId: Int,
//    ): CommonFlow<DataState<Ingredient>> = flow {
//        try {
//            emit(DataState.loading())
//
//            // TODO: just to show loading, cache is fast
//            // Note: iOS loads the DetailView ahead of time so delaying here for iOS is pointless
//            if(BuildConfig().isDebug() && BuildConfig().isAndroid()){
//                delay(500)
//            }
//
//            // TODO:  Force error for testing
//            if(recipeId == 1){
//                throw Exception("Invalid Recipe Id")
//            }
//
//            val recipe =  recipeCache.get(recipeId)
//
//            emit(DataState.data(data = recipe))
//
//        }catch (e: Exception){
//            logger.log(e.toString())
//        }
//    }.asCommonFlow()
//
//
//}