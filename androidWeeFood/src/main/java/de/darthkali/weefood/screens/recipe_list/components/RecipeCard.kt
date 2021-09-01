package de.darthkali.weefood.screens.recipe_list.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import de.darthkali.weefood.components.CircleImage
import de.darthkali.weefood.components.button.ButtonStyle
import de.darthkali.weefood.components.button.CommonButton
import de.darthkali.weefood.datasource.network.IngredientServiceImpl.Companion.NO_IMAGE
import de.darthkali.weefood.domain.model.Recipe
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoilApi
@ExperimentalCoroutinesApi
@Composable
fun RecipeCard(
    recipe: Recipe, //TODO: change to Recipe
    onClick: () -> Unit,
) {
    Card(
        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .padding(
                bottom = 6.dp,
                top = 6.dp,
                start = 16.dp,
                end = 16.dp
            )
            .fillMaxWidth(),
        elevation = 8.dp,
        backgroundColor = MaterialTheme.colors.surface
    ) {
        Column() {
            Row(verticalAlignment = Alignment.CenterVertically) {
                CircleImage(
                    url = recipe.image ?: NO_IMAGE,
                    contentDescription = recipe.name
                )
                Column {
                    Text(
                        text = recipe.name,
                        modifier = Modifier
                            .fillMaxWidth(0.85f)
                            .wrapContentWidth(Alignment.Start),
                        style = MaterialTheme.typography.h2
                    )
                    if (recipe.cooking_time != 0) {
                        CustomChip("${recipe.cooking_time} ${recipe.cooking_time_unit}")
                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 8.dp, bottom = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End

            ) {
                CommonButton(
                    text = "Öffnen",
                    buttonStyle = ButtonStyle.OPEN_BUTTON,
                    onClick = { onClick() },
                    modifier = Modifier.padding(end = 8.dp)
                )
                CommonButton(
                    text = "Hinzufügen",
                    buttonStyle = ButtonStyle.ADD_BUTTON,
                    onClick = {}
                )
            }
        }
    }
}
