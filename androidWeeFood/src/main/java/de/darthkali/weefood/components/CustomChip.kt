package de.darthkali.weefood.screens.recipe_list.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import de.darthkali.weefood.theme.AppTheme
import kotlinx.coroutines.ExperimentalCoroutinesApi

@Composable
fun CustomChip(
    text: String,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier,
        elevation = 8.dp,
        shape = MaterialTheme.shapes.large,
        color = MaterialTheme.colors.primaryVariant
    ) {
        Row(
            modifier = Modifier
        ) {
            Text(
                text = text,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.onBackground,
                modifier = Modifier
                    .defaultMinSize(minWidth = 50.dp)
                    .padding(8.dp),
            )
        }
    }
}

@ExperimentalFoundationApi
@ExperimentalCoilApi
@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Preview(showBackground = true)
@Composable
fun CustomChipPreview() {
    AppTheme() {
        Column() {
            CustomChip(text = "5 h")
        }
    }
}
