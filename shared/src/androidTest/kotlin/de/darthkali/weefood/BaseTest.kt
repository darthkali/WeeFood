package de.darthkali.weefood

import androidx.test.ext.junit.runners.AndroidJUnit4
import de.darthkali.weefood.di.initKoin
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.runner.RunWith
import org.koin.core.component.KoinComponent

@RunWith(AndroidJUnit4::class)
actual abstract class BaseTest: KoinComponent {
    @get:Rule
    var coroutineTestRule = CoroutineTestRule()


    actual fun <T> runTest(
        block: suspend CoroutineScope.() -> T) {
        runBlocking { block() }
        initKoin{ modules()}
    }
}