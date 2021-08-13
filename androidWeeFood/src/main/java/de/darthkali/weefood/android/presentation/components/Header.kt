package de.darthkali.weefood.android.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import de.darthkali.weefood.android.presentation.components.button.ButtonStyle
import de.darthkali.weefood.android.presentation.components.button.CommonButton
import de.darthkali.weefood.android.presentation.components.button.MyFloatingActionButton
import de.darthkali.weefood.android.presentation.theme.AppTheme
import kotlinx.coroutines.ExperimentalCoroutinesApi

@Composable
fun Header(
    text: String,
    modifier: Modifier = Modifier,
) {

    Column(modifier = modifier) {
        Text(
            text = text,
            color = MaterialTheme.colors.onBackground,
            style = MaterialTheme.typography.h1


            )
        Divider(color = MaterialTheme.colors.primaryVariant, thickness = 1.dp)
    }


}

@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AppTheme {

        Header(text = "Zutaten")


    }

}

