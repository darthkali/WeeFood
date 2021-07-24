package de.darthkali.weefood.android.presentation.screens.recipe_detail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import de.darthkali.weefood.domain.model.GenericMessageInfo
import de.darthkali.weefood.domain.util.GenericMessageInfoQueueUtil
import de.darthkali.weefood.domain.util.Queue
import de.darthkali.weefood.interactors.recipe_detail.GetRecipe
import de.darthkali.weefood.presentation.recipe_detail.RecipeDetailState
import de.darthkali.weefood.presentation.recipe_detail.RecipeDetailEvents
import de.darthkali.weefood.shared.domain.util.UIComponentType
import de.darthkali.weefood.util.Logger
import java.util.*
import javax.inject.Inject

@HiltViewModel
class RecipeDetailViewModel
@Inject
constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getRecipe: GetRecipe,
): ViewModel() {

    private val logger = Logger("RecipeDetailViewModel")

    val state: MutableState<RecipeDetailState> = mutableStateOf(RecipeDetailState())

    init {
        savedStateHandle.get<Int>("recipeId")?.let { recipeId ->
            onTriggerEvent(RecipeDetailEvents.GetRecipe(recipeId = recipeId))
        }
    }

    fun onTriggerEvent(event: RecipeDetailEvents){
        when (event){
            is RecipeDetailEvents.GetRecipe -> {
                getRecipe(recipeId = event.recipeId)
            }
            is RecipeDetailEvents.OnRemoveHeadMessageFromQueue -> {
                removeHeadMessage()
            }
            else -> {
                val messageInfoBuilder = GenericMessageInfo.Builder()
                    .id(UUID.randomUUID().toString())
                    .title("Invalid Event")
                    .uiComponentType(UIComponentType.Dialog)
                    .description("Something went wrong.")
                appendToMessageQueue(messageInfo = messageInfoBuilder)
            }
        }
    }

    private fun removeHeadMessage() {
        try {
            val queue = state.value.queue
            queue.remove() // can throw exception if empty
            state.value = state.value.copy(queue = Queue(mutableListOf())) // force recompose
            state.value = state.value.copy(queue = queue)
        }catch (e: Exception){
            logger.log("Nothing to remove from DialogQueue")
        }
    }

    private fun getRecipe(recipeId: Int){
        getRecipe.execute(recipeId = recipeId).collectCommon(viewModelScope) { dataState ->
            state.value = state.value.copy(isLoading = dataState.isLoading)

            dataState.data?.let { recipe ->
                state.value = state.value.copy(recipe = recipe)
            }

            dataState.message?.let { message ->
                appendToMessageQueue(message)
            }
        }
    }

    private fun appendToMessageQueue(messageInfo: GenericMessageInfo.Builder){
        if(!GenericMessageInfoQueueUtil()
                .doesMessageAlreadyExistInQueue(queue = state.value.queue,messageInfo = messageInfo.build())){
            val queue = state.value.queue
            queue.add(messageInfo.build())
            state.value = state.value.copy(queue = queue)
        }
    }
}