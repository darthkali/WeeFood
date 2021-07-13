package de.darthkali.weefood.domain.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

fun <T> Flow<T>.asCommonFlow(): CommonFlow<T> = CommonFlow(this)

class CommonFlow<T>(private val origin: Flow<T>) : Flow<T> by origin {
    fun collectCommon(
        coroutineScope: CoroutineScope? = null,
        callback: (T) -> Unit,
    ) {
        onEach {
            callback(it)
        }.launchIn(coroutineScope ?: CoroutineScope(Dispatchers.Main))
    }
}