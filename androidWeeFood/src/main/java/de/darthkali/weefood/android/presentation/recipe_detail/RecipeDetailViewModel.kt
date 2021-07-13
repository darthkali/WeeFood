package de.darthkali.weefood.android.presentation.recipe_detail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import de.darthkali.weefood.interactors.recipe_detail.GetRecipe
import de.darthkali.weefood.presentation.recipe_detail.RecipeDetailState
import de.darthkali.weefood.presentation.recipe_detail.RecipeDetailEvents
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@ExperimentalStdlibApi
@HiltViewModel
class RecipeDetailViewModel
@Inject
constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getRecipe: GetRecipe,
) : ViewModel() {
    val state: MutableState<RecipeDetailState> = mutableStateOf(RecipeDetailState())

    init {
        savedStateHandle.get<Int>("recipeId")?.let { recipeId ->
            onTriggerEvent(RecipeDetailEvents.GetRecipe(recipeId = recipeId))
        }

    }

    fun onTriggerEvent(event: RecipeDetailEvents){
        when(event){
            is RecipeDetailEvents.GetRecipe ->{
                getRecipe(recipeId = event.recipeId)
            }else -> {
                handleError("invalid Events")
            }
        }
    }

    private fun getRecipe(recipeId: Int){
        getRecipe.execute(recipeId = recipeId).collectCommon (viewModelScope){ dataState ->

            state.value = state.value.copy(isLoading = dataState.isLoading)

            dataState.data?.let { recipe ->
                state.value = state.value.copy(recipe = recipe)
            }

            dataState.message?.let { message ->
                handleError(message)
            }
        }
    }


    private fun handleError(errorMessage: String){
        //TODO
    }
}