package de.darthkali.weefood.android.presentation.screens.new_recipe

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import de.darthkali.weefood.android.presentation.navigation.BottomBar
import de.darthkali.weefood.android.presentation.navigation.TopBar
import de.darthkali.weefood.android.presentation.screens.new_recipe.components.IngredientCard
import de.darthkali.weefood.android.presentation.screens.new_recipe.components.IngredientUnitTextField
import de.darthkali.weefood.android.presentation.theme.AppTheme
import de.darthkali.weefood.mockFactory.IngredientMock
import de.darthkali.weefood.mockFactory.RecipeIngredientMock
import de.darthkali.weefood.presentation.ingredient_list.IngredientListEvents
import de.darthkali.weefood.presentation.ingredient_list.IngredientListState
import de.darthkali.weefood.presentation.new_recipe.NewRecipeEvents
import de.darthkali.weefood.presentation.new_recipe.NewRecipeState
import kotlinx.coroutines.ExperimentalCoroutinesApi


@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@Composable
fun NewRecipeScreen(
    state: NewRecipeState,
    navController: NavController,
    onTriggerEvent: (NewRecipeEvents) -> Unit,
) {
    AppTheme() {
        Scaffold(
            topBar = { TopBar(title = "Neues Rezept") },
            bottomBar = { BottomBar(navController) }
        ) {
            LazyColumn() {
                item {
                    Text("Bild")
                    IngredientUnitTextField(
                        input = state.name,
                        onInputChanged = {
                            onTriggerEvent(NewRecipeEvents.OnUpdateName(it))
                        },
                        label = "Rezeptname")
                    Row() {
                        Text("Zuatten pro Portion")
                        Text("Plus Button")
                    }
                }

                itemsIndexed(
                    items = IngredientMock.ingredientList
                ) { _, recipeIngredient -> //hier haben wir ein recipe INgredient. Da muss ein Join erfolgen
                    IngredientCard(
                        ingredient = recipeIngredient,
                        onDeleteIngredient = { } //onDeleteIngredient(it)
                    )
                }



                item{
                    Text("Kochzeit")

                    Row() {
                        IngredientUnitTextField(
                            input = state.cooking_time.toString(),
                            onInputChanged = {
                                onTriggerEvent(NewRecipeEvents.OnUpdateCookingTime(it.toInt()))
                            },
                            label = "Zeit")
                        IngredientUnitTextField(
                            input = state.cooking_time_unit,
                            onInputChanged = {
                                onTriggerEvent(NewRecipeEvents.OnUpdateCookingTimeUnit(it))
                            },
                            label = "Einheit")
                    }
                    Text("Rezept")
                    IngredientUnitTextField(
                        input = state.description,
                        onInputChanged = {
                            onTriggerEvent(NewRecipeEvents.OnUpdateDescription(it))
                        },
                        label = "Rezept")
                }
            }
        }

    }
}




//@ExperimentalFoundationApi
//@ExperimentalCoroutinesApi
//@ExperimentalMaterialApi
//@ExperimentalComposeUiApi
//@Preview(showBackground = true)
//@Composable
//fun NewRecipeScreenPreview() {
//
//    val navController = rememberNavController()
//    AppTheme() {
//        NewRecipeScreen(navController)
//    }
//}