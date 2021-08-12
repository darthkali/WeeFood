package de.darthkali.weefood.android.presentation.screens.recipe_detail

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
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
            Text("Bild")
            Row() {
                Text("Zuatten pro Portion")
                Text("Plus Button")
            }
        }

        itemsIndexed(
            items = recipe.ingredients
        ) { _, ingredients ->

            Row() {
                Text(ingredients.name ?: "")
                Text(ingredients.quantity.toString())
                Text(ingredients.unit)
            }
        }

        item {
            Text("Kochzeit")
            Row() {
                Text(recipe.cooking_time.toString())
                Text(recipe.cooking_time_unit)
            }

            Text("Rezept")
            Text(recipe.recipeDescription ?: "")
        }
    }
}
