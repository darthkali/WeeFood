package de.darthkali.weefood.android.presentation.screens.recipe_list.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import de.darthkali.weefood.android.presentation.components.CircleImage
import de.darthkali.weefood.android.presentation.components.CommonButton
import de.darthkali.weefood.android.presentation.components.button.ButtonStyle
import de.darthkali.weefood.datasource.network.IngredientServiceImpl.Companion.NO_IMAGE
import de.darthkali.weefood.datasource.database.model.RecipeDb
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Composable
fun RecipeCard(
    recipe: RecipeDb, //TODO: change to Recipe
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
        //.clickable(onClick = onClick),
        elevation = 8.dp,
        backgroundColor = MaterialTheme.colors.surface
    ) {
        Column() {
            Row(verticalAlignment = Alignment.CenterVertically) {
                CircleImage(
                    url = recipe.image ?: NO_IMAGE,
                    contentDescription = recipe.name ?: "not valid"
                )
                Text(
                    text = recipe.name ?: "",    //if recipe.name == null, then set "" as text
                    modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .wrapContentWidth(Alignment.Start),
                    style = MaterialTheme.typography.h2
                )
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
                    buttonStyle = ButtonStyle.ADD_BUTTON
                ) {}
            }
        }
    }
}
