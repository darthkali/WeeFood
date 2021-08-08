package de.darthkali.weefood.android.presentation.screens.ingredient_list.components


import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import de.darthkali.weefood.android.presentation.components.CircleImage
import de.darthkali.weefood.android.presentation.components.CommonButton
import de.darthkali.weefood.android.presentation.components.button.ButtonStyle
import de.darthkali.weefood.android.presentation.navigation.NavigationItem
import de.darthkali.weefood.domain.model.Ingredient
import kotlinx.coroutines.ExperimentalCoroutinesApi


@ExperimentalCoroutinesApi
@Composable
fun IngredientCard(
    ingredientDb: Ingredient,
    onSaveIngredient: (Ingredient) -> Unit
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
                    url = ingredientDb.image,
                    contentDescription = ingredientDb.name
                )
                Text(
                    text = ingredientDb.name ?: "",   //if ingredient.name == null, then set "" as text
                    modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .wrapContentWidth(Alignment.Start),
                    style = typography.h2
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end= 8.dp, bottom = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End

            ) {
                CommonButton( text = "Schliessen",  buttonStyle = ButtonStyle.CLOSE_BUTTON) {}
                CommonButton( text = "Hinzufügen",  buttonStyle = ButtonStyle.ADD_BUTTON) {
                    onSaveIngredient(
                        ingredientDb
                    )
                }
            }
        }
    }
}



//@ExperimentalCoroutinesApi
//@ExperimentalMaterialApi
//@ExperimentalComposeUiApi
//@Preview(showBackground = true)
//@Composable
//fun UserProfileDetailsPreview() {
//    val apple = Ingredient(
//        id = 1,
//        name = "Apfel",
//        image = "apple.img",
////        aisle = "meat",
////        possibleUnits = listOf(),
//    )
//    AppTheme() {
//        IngredientCard(ingredient = apple)
//    }
//
//}
