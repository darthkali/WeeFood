package de.darthkali.weefood.android.presentation.screens.recipe_detail

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import de.darthkali.weefood.android.presentation.components.Header
import de.darthkali.weefood.android.presentation.components.button.ButtonStyle
import de.darthkali.weefood.android.presentation.components.button.CommonButton
import de.darthkali.weefood.android.presentation.screens.recipe_detail.components.IngredientCard
import de.darthkali.weefood.android.presentation.screens.recipe_detail.components.IngredientUnitTextField
import de.darthkali.weefood.domain.model.Recipe
import de.darthkali.weefood.presentation.recipe_detail.RecipeDetailEvents
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@Composable
fun EditableRecipeDetail(
    recipe: Recipe,
    onTriggerEvent: (RecipeDetailEvents) -> Unit,
    onClickAddIngredient: (Int?) -> Unit,
) {
    LazyColumn() {
        item {
//            Text("Bild")
            IngredientUnitTextField(
                input = recipe.name,
                onInputChanged = {
                    onTriggerEvent(RecipeDetailEvents.OnUpdateName(it))
                },
                label = "Rezeptname",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
            )

            Header(
                text = "Zutaten pro Portion",
                modifier = Modifier.padding(start = 8.dp, end = 8.dp)
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 8.dp)
            ) {
                CommonButton(
                    text = "Zutat hinzufügen",
                    buttonStyle = ButtonStyle.ADD_BUTTON,

                    ) {
                    onTriggerEvent(RecipeDetailEvents.OnSaveRecipe)
                    recipe.databaseId?.let {
                        if (it != 0) {
                            onClickAddIngredient(it)
                        }
                    }
                }
            }
        }

        itemsIndexed(
            items = recipe.ingredients
        ) { _, ingredients ->
            IngredientCard(
                ingredient = ingredients,
                onDeleteIngredient = {
                    onTriggerEvent(
                        RecipeDetailEvents.OnDeleteIngredient(
                            it
                        )
                    )
                }, //onDeleteIngredient(it),
                onTriggerEvent = onTriggerEvent
            )
        }



        item {
            Header(
                text = "Kochzeit",
                modifier = Modifier.padding(start = 8.dp, end = 8.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {

                IngredientUnitTextField(
                    input = recipe.cooking_time.toString(),
                    onInputChanged = {
                        var result = 0
                        if(it != ""){
                            result = it.toInt()
                        }

                        onTriggerEvent(
                            RecipeDetailEvents.OnUpdateCookingTime(
                                result
                            )
                        )
                    },
                    label = "Zeit",
                    modifier = Modifier
                        .width(120.dp)
                        .padding(8.dp),
                )

                IngredientUnitTextField(
                    input = recipe.cooking_time_unit,
                    onInputChanged = {
                        onTriggerEvent(
                            RecipeDetailEvents.OnUpdateCookingTimeUnit(
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
            Header(
                text = "Rezept",
                modifier = Modifier.padding(start = 8.dp, end = 8.dp)
            )
            IngredientUnitTextField(
                input = recipe.recipeDescription ?: "",
                onInputChanged = {
                    onTriggerEvent(RecipeDetailEvents.OnUpdateDescription(it))
                },
                label = "Rezept",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .defaultMinSize(minHeight = 100.dp),
            )
        }
    }
}

