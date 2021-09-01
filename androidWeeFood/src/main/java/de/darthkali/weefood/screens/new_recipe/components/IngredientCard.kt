package de.darthkali.weefood.screens.recipe_detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import de.darthkali.weefood.components.CircleImage
import de.darthkali.weefood.components.button.CommonButton
import de.darthkali.weefood.components.button.ButtonStyle
import de.darthkali.weefood.domain.model.Ingredient
import de.darthkali.weefood.presentation.recipe_detail.RecipeDetailEvents

@ExperimentalCoilApi
@ExperimentalComposeUiApi
@Composable
fun IngredientCard(
    ingredient: Ingredient,
    onTriggerEvent: (RecipeDetailEvents) -> Unit,
    onDeleteIngredient: (Ingredient) -> Unit
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
                    url = ingredient.image,
                    contentDescription = ingredient.name
                )

                Column {


                    Text(
                        text = ingredient.name
                            ?: "",   //if ingredient.name == null, then set "" as text
                        modifier = Modifier
                            .fillMaxWidth(0.85f)
                            .wrapContentWidth(Alignment.Start),
                        style = typography.h2
                    )

                    Row() {
                        IngredientUnitTextField(
                            input = ingredient.quantity.toString(),
                            onInputChanged = {
                                onTriggerEvent(
                                    RecipeDetailEvents.OnUpdateIngredientQuantity(
                                        ingredient.internalId!!,
                                        it.toFloat()
                                    )
                                )
                            },
                            label = "Einheit",
                            modifier = Modifier
                                .width(120.dp)
                                .padding(8.dp),
                        )
                        IngredientUnitTextField(
                            input = ingredient.unit,
                            onInputChanged = {
                                onTriggerEvent(
                                    RecipeDetailEvents.OnUpdateIngredientQuantityUnit(
                                        ingredient.internalId!!,
                                        it
                                    )
                                )
                            },
                            label = "Einheit",
                            modifier = Modifier
                                .width(120.dp)
                                .padding(8.dp),
                        )
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
                    text = "Löschen",
                    buttonStyle = ButtonStyle.DELETE_BUTTON
                ) { onDeleteIngredient(ingredient) }
                CommonButton(
                    text = "Schliessen",
                    buttonStyle = ButtonStyle.CLOSE_BUTTON
                ) {}
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
//    AppTheme() {
//        IngredientCard(ingredient = IngredientMock.ingredient, onDeleteIngredient = {})
//    }
//}
