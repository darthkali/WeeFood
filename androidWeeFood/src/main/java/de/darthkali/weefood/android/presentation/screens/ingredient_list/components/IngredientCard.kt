package de.darthkali.weefood.android.presentation.screens.ingredient_list.components


import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import de.darthkali.weefood.android.presentation.components.CircleImage
import de.darthkali.weefood.android.presentation.theme.AppTheme
import de.darthkali.weefood.domain.model.Ingredient
import kotlinx.coroutines.ExperimentalCoroutinesApi


const val RECIPE_IMAGE_HEIGHT = 260

@ExperimentalCoroutinesApi
@Composable
fun IngredientCard(
    ingredient: Ingredient,
) {
    Card(
        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .padding(
                bottom = 6.dp,
                top = 6.dp,
            )
            .fillMaxWidth(),
        elevation = 8.dp,
    ) {
        Column() {
            Row(verticalAlignment = Alignment.CenterVertically) {
                CircleImage(
                    url = ingredient.image,
                    contentDescription = ingredient.name
                )
                Text(
                    text = ingredient.name,
                    modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .wrapContentWidth(Alignment.Start),
                    style = MaterialTheme.typography.h2
                )
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Button(onClick = {}){
                    Text("Schliessen")
                }
                Button(onClick = {}){
                    Text("Hinzufügen")
                }
            }


        }
    }
}


@OptIn(
    ExperimentalCoroutinesApi::class,
    androidx.compose.ui.ExperimentalComposeUiApi::class,
    androidx.compose.material.ExperimentalMaterialApi::class
)
@Preview(showBackground = true)
@Composable
fun UserProfileDetailsPreview() {
    val apple = Ingredient(
        id = 1,
        name = "Apfel",
        image = "apple.img",
        aisle = "meat",
        possibleUnits = listOf(),
    )
    AppTheme() {
        IngredientCard(ingredient = apple)
    }

}
