package de.darthkali.weefood

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.core.app.ApplicationProvider
import de.darthkali.weefood.di.appModule
import de.darthkali.weefood.di.database
import de.darthkali.weefood.di.interactor
import de.darthkali.weefood.di.network
import de.darthkali.weefood.di.platformModule
import de.darthkali.weefood.navigation.Navigation
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule

@ExperimentalCoroutinesApi
@ExperimentalStdlibApi
@ExperimentalFoundationApi
@ExperimentalComposeUiApi
@ExperimentalMaterialApi

open class BaseUiTest : KoinTest {
    @get:Rule
    val koinTestRule = KoinTestRule.create {
        androidContext(ApplicationProvider.getApplicationContext())
        androidLogger()
        modules(
            platformModule(),
            network,
            database,
            interactor,
            appModule
        )
    }

    @get:Rule
    val composeTestRule = createComposeRule()

    fun ComposeContentTestRule.launchWeeFoodApp() {
        setContent {
            Navigation()
        }
    }
}
