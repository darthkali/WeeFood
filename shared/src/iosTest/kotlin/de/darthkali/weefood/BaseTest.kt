package de.darthkali.weefood

import de.darthkali.weefood.di.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.test.KoinTest
import platform.CoreFoundation.CFRunLoopGetCurrent
import platform.CoreFoundation.CFRunLoopRun
import platform.CoreFoundation.CFRunLoopStop
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

actual abstract class BaseTest: KoinTest {

    
    @OptIn(DelicateCoroutinesApi::class)
    actual fun <T> runTest(block: suspend CoroutineScope.() -> T) {
        var error: Throwable? = null

        GlobalScope.launch(Dispatchers.Main) {
            try {
                initKoin()
                block()
            } catch (t: Throwable) {
                error = t
            } finally {
                stopKoin()
                CFRunLoopStop(CFRunLoopGetCurrent())
            }
        }
        CFRunLoopRun()
        error?.also { throw it }
    }
}