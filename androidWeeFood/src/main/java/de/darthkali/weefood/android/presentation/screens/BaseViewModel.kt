package de.darthkali.weefood.android.presentation.screens

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.darthkali.weefood.presentation.ViewEvent
import de.darthkali.weefood.presentation.ViewState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent


abstract class BaseViewModel<Event : ViewEvent, UiState : ViewState> : ViewModel(), KoinComponent {

    private val initialState: UiState by lazy { setInitialState() }
    abstract fun setInitialState(): UiState

    private val _viewState: MutableState<UiState> = mutableStateOf(initialState)
    val viewState: State<UiState> = _viewState

    private val _event: MutableSharedFlow<Event> = MutableSharedFlow()


    init {
        subscribeToEvents()
    }

    fun setEvent(event: Event) {
        viewModelScope.launch { _event.emit(event) }
    }

    protected fun setState(reducer: UiState.() -> UiState) {
        val newState = viewState.value.reducer()
        _viewState.value = newState
    }

    private fun subscribeToEvents() {
        viewModelScope.launch {
            _event.collect {
                onTriggerEvent(it)
            }
        }
    }

    abstract fun onTriggerEvent(event: Event)
}