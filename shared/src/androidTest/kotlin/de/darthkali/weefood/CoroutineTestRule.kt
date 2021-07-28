package de.darthkali.weefood

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExecutorCoroutineDispatcher
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import java.util.concurrent.Executors
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * Use this rule to update the Main dispatcher ahead of tests. By delegating the main dispatcher to a new thread.
 * we can block the current thread and still dispatch main coroutines
 */
class CoroutineTestRule(
    private val testDispatcher: ExecutorCoroutineDispatcher = Executors.newSingleThreadExecutor()
        .asCoroutineDispatcher()
) : TestWatcher() {
    @OptIn(ExperimentalCoroutinesApi::class)
    override fun starting(description: Description?) {
        super.starting(description)
        Dispatchers.setMain(testDispatcher)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun finished(description: Description?) {
        super.finished(description)
        Dispatchers.resetMain()
    }
}