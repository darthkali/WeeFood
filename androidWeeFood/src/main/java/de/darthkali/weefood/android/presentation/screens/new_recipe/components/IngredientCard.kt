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
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import de.darthkali.weefood.android.presentation.components.CircleImage
import de.darthkali.weefood.android.presentation.components.button.CommonButton
import de.darthkali.weefood.android.presentation.components.button.ButtonStyle
import de.darthkali.weefood.android.presentation.theme.AppTheme
import de.darthkali.weefood.domain.model.Ingredient
import de.darthkali.weefood.mockFactory.IngredientMock
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalComposeUiApi
@Composable
fun IngredientCard(
    ingredient: Ingredient,
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
                Text(
                    text = ingredient.name
                        ?: "",   //if ingredient.name == null, then set "" as text
                    modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .wrapContentWidth(Alignment.Start),
                    style = typography.h2
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


@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Preview(showBackground = true)
@Composable
fun UserProfileDetailsPreview() {
    AppTheme() {
        IngredientCard(ingredient = IngredientMock.ingredient, onDeleteIngredient = {})
    }
}
