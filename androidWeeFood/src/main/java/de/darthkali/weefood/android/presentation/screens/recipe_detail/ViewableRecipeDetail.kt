package de.darthkali.weefood.android.presentation.screens.recipe_detail

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import de.darthkali.weefood.android.presentation.components.Header
import de.darthkali.weefood.android.presentation.screens.recipe_list.components.CustomChip
import de.darthkali.weefood.domain.model.Recipe
import kotlinx.coroutines.ExperimentalCoroutinesApi


@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@Composable
fun ViewableRecipeDetail(
    recipe: Recipe,
) {
    LazyColumn() {
        item {
//            Text("Bild")
            if (recipe.cooking_time != 0) {
                CustomChip("${recipe.cooking_time} ${recipe.cooking_time_unit}")
            }
            Header(
                text = "Zutaten pro Portion",
                modifier = Modifier.padding(start = 8.dp, end = 8.dp)
            )


            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
            ) {

                Text(
                    text = "Zutat",
                    modifier = Modifier
                        .padding(start = 8.dp, end = 8.dp),
                    style = MaterialTheme.typography.h2
                )
                Text(
                    text = "Menge",
                    modifier = Modifier.padding(start = 8.dp, end = 8.dp),
                    style = MaterialTheme.typography.h2
                )
            }
        }
        itemsIndexed(
            items = recipe.ingredients
        ) { _, ingredients ->

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp),
            ) {
                Text(
                    text = ingredients.name ?: "",
                    style = MaterialTheme.typography.body1

                )
                Text(
                    text = "${ingredients.quantity} ${ingredients.unit}",
                    style = MaterialTheme.typography.body1
                )
            }
        }

        item {
            Header(
                text = "Rezept",
                modifier = Modifier.padding(start = 8.dp, end = 8.dp)
            )
            Text(
                text = recipe.recipeDescription ?: "",
                modifier = Modifier.padding(start = 8.dp, end = 8.dp),
            )
        }
    }
}
