package de.darthkali.weefood.android.presentation.screens.recipe_list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import de.darthkali.weefood.domain.model.Ingredient
import de.darthkali.weefood.presentation.ingredient_list.IngredientListEvents
import de.darthkali.weefood.interactors.ingredient_list.SearchIngredient
import de.darthkali.weefood.presentation.ingredient_list.IngredientListState
import de.darthkali.weefood.util.Logger
import javax.inject.Inject
import kotlin.collections.ArrayList

@HiltViewModel
class RecipeListViewModel
@Inject
constructor(
    private val searchIngredient: SearchIngredient,
): ViewModel() {

    private val logger = Logger("RecipeListVM")

    val state: MutableState<IngredientListState> = mutableStateOf(IngredientListState())

    init {
        loadRecipes()
    }

    fun onTriggerEvent(event: IngredientListEvents){
        when (event){
            IngredientListEvents.LoadIngredient -> {
                loadRecipes()
            }
            IngredientListEvents.NewSearch -> {
                newSearch()
            }
            IngredientListEvents.NextPage -> {
                nextPage()
            }
//            is IngredientListEvents.OnSelectCategory -> {
//                onSelectCategory(event.category)
//            }
//            is IngredientListEvents.OnUpdateQuery -> {
//                state.value = state.value.copy(query =  event.query, selectedCategory = null)
//            }
//            is IngredientListEvents.OnRemoveHeadMessageFromQueue -> {
//                removeHeadMessage()
//            }
            else -> {

                //TODO: ErrorMessage
//                val messageInfoBuilder = GenericMessageInfo.Builder()
//                    .id(UUID.randomUUID().toString())
//                    .title("Invalid Event")
//                    .uiComponentType(UIComponentType.Dialog)
//                    .description("Something went wrong.")
//                appendToMessageQueue(messageInfo = messageInfoBuilder)
            }
        }
    }

//    private fun removeHeadMessage() {
//        try {
//            val queue = state.value.queue
//            queue.remove() // can throw exception if empty
//            state.value = state.value.copy(queue = Queue(mutableListOf())) // force recompose
//            state.value = state.value.copy(queue = queue)
//        }catch (e: Exception){
//            logger.log("Nothing to remove from DialogQueue")
//        }
//    }

    /**
     *  Called when a new FoodCategory chip is selected
     */
//    private fun onSelectCategory(category: FoodCategory){
//        state.value = state.value.copy(selectedCategory = category, query =  category.value)
//        newSearch()
//    }

    /**
     * Get the next page of recipes
     */
    private fun nextPage(){
        state.value = state.value.copy(page = state.value.page + 1)
        loadRecipes()
    }

    /**
     * Perform a new search:
     * 1. page = 1
     * 2. list position needs to be reset
     */
    private fun newSearch(){
        state.value = state.value.copy(page = 1, ingredients = listOf())
        loadRecipes()
    }

    private fun loadRecipes(){
        searchIngredient.execute(
            query = state.value.query,
            page = state.value.page,
        ).collectCommon(viewModelScope) { dataState ->
            state.value = state.value.copy(isLoading = dataState.isLoading)

            dataState.data?.let { recipes ->
                appendRecipes(recipes)
            }

//            dataState.message?.let { message ->
//                appendToMessageQueue(message)
//            }
        }
    }

    private fun appendRecipes(ingredients: List<Ingredient>){
        val curr = ArrayList(state.value.ingredients)
        curr.addAll(ingredients)
        state.value = state.value.copy(ingredients = curr)
    }

//    private fun appendToMessageQueue(messageInfo: GenericMessageInfo.Builder){
//        if(!GenericMessageInfoQueueUtil()
//                .doesMessageAlreadyExistInQueue(queue = state.value.queue,messageInfo = messageInfo.build())){
//            val queue = state.value.queue
//            queue.add(messageInfo.build())
//            state.value = state.value.copy(queue = queue)
//        }
//    }

}