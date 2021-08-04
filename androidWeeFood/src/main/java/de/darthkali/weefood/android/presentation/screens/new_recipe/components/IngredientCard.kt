package de.darthkali.weefood.android.presentation.screens.new_recipe.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import de.darthkali.weefood.android.presentation.components.CircleImage
import de.darthkali.weefood.android.presentation.components.CommonButton
import de.darthkali.weefood.android.presentation.components.button.ButtonStyle
import de.darthkali.weefood.android.presentation.theme.AppTheme
import de.darthkali.weefood.datasource.database.model.IngredientDb
import de.darthkali.weefood.mockFactory.IngredientMock
import kotlinx.coroutines.ExperimentalCoroutinesApi


@ExperimentalComposeUiApi
@Composable
fun IngredientCard(
    ingredientDb: IngredientDb,
    onDeleteIngredient: (IngredientDb) -> Unit
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
        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                CircleImage(
                    url = ingredientDb.image,
                    contentDescription = ingredientDb.name
                )
                Text(
                    text = ingredientDb.name
                        ?: "",   //if ingredient.name == null, then set "" as text
                    modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .wrapContentWidth(Alignment.Start),
                    style = typography.h2
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                val textState = remember { mutableStateOf(TextFieldValue()) }
                //textState.value.copy(RecipeIngredientMock.recipeIngredient.quantity.toString())

//                IngredientUnitTextField(
//                    value = textState.value,
//                    label = "Menge")
//
//                IngredientUnitTextField(
//                    input = state.recipe.cooking_time_unit,
//                    onInputChanged = {
//                        onTriggerEvent(NewRecipeEvents.OnUpdateCookingTimeUnit(it))
//                    },
//                    label = "Einheit"
//                )
//
//                IngredientUnitTextField(
//                    value = textState.value,
//                    label = "Einheit")
            }




            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 8.dp, bottom = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End

            ) {
                CommonButton(
                    text = "Löschen",
                    buttonStyle = ButtonStyle.DELETE_BUTTON,
                    onClick = { onDeleteIngredient(ingredientDb) })
                CommonButton(
                    text = "Schliessen",
                    buttonStyle = ButtonStyle.CLOSE_BUTTON,
                    onClick = {})
            }
        }
    }
}


@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Preview(showBackground = true)
@Composable
fun UserProfileDetailsPreview() {
    AppTheme() {
        IngredientCard(ingredientDb = IngredientMock.ingredient, onDeleteIngredient = {})
    }
}
