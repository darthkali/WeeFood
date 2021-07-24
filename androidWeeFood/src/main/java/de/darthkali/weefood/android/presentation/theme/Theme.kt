package de.darthkali.weefood.android.presentation.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import de.darthkali.weefood.android.presentation.components.CircularIndeterminateProgressBar
import de.darthkali.weefood.android.presentation.components.ProcessDialogQueue
import de.darthkali.weefood.domain.model.GenericMessageInfo
import de.darthkali.weefood.domain.util.Queue



@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@Composable
fun AppTheme(
    displayProgressBar: Boolean = false,
   // dialogQueue: Queue<GenericMessageInfo> = Queue(mutableListOf()),
    darkTheme: Boolean = isSystemInDarkTheme(),
    //onRemoveHeadMessageFromQueue: () -> Unit,
    content: @Composable () -> Unit,
) {

    MaterialTheme(
        colors = if (darkTheme) DarkColors else LightColors,
        typography = MontserratTypography,
        shapes = AppShapes
    ){
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
        ){
            // For android we can process the DialogQueue at the Application level
            // on iOS you cannot do this because SwiftUI preloads the views in a List
//            ProcessDialogQueue(
//                dialogQueue = dialogQueue,
//                onRemoveHeadMessageFromQueue = onRemoveHeadMessageFromQueue,
//            )
            Column{
                content()
            }
            CircularIndeterminateProgressBar(isDisplayed = displayProgressBar, 0.3f)
        }
    }
}