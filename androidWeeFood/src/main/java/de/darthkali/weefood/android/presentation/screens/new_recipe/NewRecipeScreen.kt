package de.darthkali.weefood.android.presentation.screens.new_recipe

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import de.darthkali.weefood.android.presentation.components.button.CommonButton
import de.darthkali.weefood.android.presentation.components.button.MyFloatingActionButton
import de.darthkali.weefood.android.presentation.components.button.ButtonStyle
import de.darthkali.weefood.android.presentation.navigation.BottomBar
import de.darthkali.weefood.android.presentation.navigation.NavigationItem
import de.darthkali.weefood.android.presentation.navigation.TopBar
import de.darthkali.weefood.android.presentation.screens.new_recipe.components.IngredientCard
import de.darthkali.weefood.android.presentation.screens.new_recipe.components.IngredientUnitTextField
import de.darthkali.weefood.android.presentation.theme.AppTheme
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
    viewModel: NewRecipeViewModel
) {

    AppTheme() {
        Scaffold(
            topBar = {
                TopBar(
                    title = "Neues Rezept",
                    navigationIcon = Icons.Filled.ArrowBack,
                    navigationIconClickAction = { navController.navigateUp() },
                    navController = navController
                )
            },
            floatingActionButtonPosition = FabPosition.End,
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        onTriggerEvent(NewRecipeEvents.OnSaveRecipe)
                        navController.navigate(
                            "${NavigationItem.RecipeList.route}?query=${state.recipe.name}"
                        )
                    },
                    backgroundColor = MaterialTheme.colors.primary,
//                        contentColor = Color.White,
                    elevation = FloatingActionButtonDefaults.elevation(6.dp)
                ) {
                    Icon(Icons.Filled.Check, "")
                }
            }
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {

                LazyColumn() {
                    item {
                        Text("Bild")
                        IngredientUnitTextField(
                            input = state.recipe.name,
                            onInputChanged = {
                                onTriggerEvent(NewRecipeEvents.OnUpdateName(it))
                            },
                            label = "Rezeptname"
                        )
                        Row() {
                            Text("Zuatten pro Portion")
                            Text("Plus Button")
                        }
                        CommonButton(
                            text = "Zutat Hinzufügen",
                            buttonStyle = ButtonStyle.ADD_BUTTON
                        ) {


                            viewModel.onTriggerEvent(NewRecipeEvents.OnSaveRecipe)
                            viewModel.state.value.recipe.databaseId?.let {
                                if (it != 0) {
                                    navController.navigate(
                                        "${NavigationItem.IngredientList.route}/${it}"
                                    )

                                }
                            }
                        }
                    }

                    itemsIndexed(
                        items = state.recipe.ingredients
                    ) { _, ingredients ->
                        IngredientCard(
                            ingredient = ingredients,
                            onDeleteIngredient = {
                                onTriggerEvent(
                                    NewRecipeEvents.OnDeleteIngredient(
                                        it
                                    )
                                )
                            } //onDeleteIngredient(it)
                        )
                    }



                    item {
                        Text("Kochzeit")

                        Row() {

                            val keyboardController = LocalSoftwareKeyboardController.current
                            TextField(
                                modifier = Modifier
                                    .width(120.dp)
                                    .padding(8.dp),
                                value = state.recipe.cooking_time.toString(),
                                onValueChange = {
                                    onTriggerEvent(
                                        NewRecipeEvents.OnUpdateCookingTime(
                                            it.toInt()
                                        )
                                    )
                                },
                                label = { Text(text = "Zeit") },
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Text,
                                    imeAction = ImeAction.Done,
                                ),
                                keyboardActions = KeyboardActions(
                                    onDone = {
                                        keyboardController?.hide()
                                    },
                                ),
                                textStyle = TextStyle(color = MaterialTheme.colors.primary),
                                colors = TextFieldDefaults.textFieldColors(backgroundColor = MaterialTheme.colors.surface),
                            )


                            IngredientUnitTextField(
                                input = state.recipe.cooking_time_unit,
                                onInputChanged = {
                                    onTriggerEvent(
                                        NewRecipeEvents.OnUpdateCookingTimeUnit(
                                            it
                                        )
                                    )
                                },
                                label = "Einheit"
                            )
                        }
                        Text("Rezept")
                        IngredientUnitTextField(
                            input = state.recipe.recipeDescription ?: "",
                            onInputChanged = {
                                onTriggerEvent(NewRecipeEvents.OnUpdateDescription(it))
                            },
                            label = "Rezept"
                        )
                    }
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