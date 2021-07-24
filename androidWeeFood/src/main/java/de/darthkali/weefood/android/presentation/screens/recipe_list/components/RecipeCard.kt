package de.darthkali.weefood.android.presentation.screens.recipe_list.components


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import de.darthkali.weefood.android.presentation.components.RecipeImage
import de.darthkali.weefood.domain.model.Recipe
import kotlinx.coroutines.ExperimentalCoroutinesApi


const val RECIPE_IMAGE_HEIGHT = 260

@ExperimentalCoroutinesApi
@Composable
fun RecipeCard(
    recipe: Recipe,
    onClick: () -> Unit,
) {
    Card(
        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .padding(
                bottom = 6.dp,
                top = 6.dp,
            )
            .fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = 8.dp,
    ) {
        Column() {
            RecipeImage(
                url = recipe.image,
                contentDescription = recipe.name
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp, bottom = 12.dp, start = 8.dp, end = 8.dp)
            ) {
                Text(
                    text = recipe.name,
                    modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .wrapContentWidth(Alignment.Start),
                    style = MaterialTheme.typography.h2
                )
//                val rank = recipe.rating.toString()
//                Text(
//                    text = rank,
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .wrapContentWidth(Alignment.End)
//                        .align(Alignment.CenterVertically),
//                    style = MaterialTheme.typography.h1
//                )
            }
        }
    }
}
