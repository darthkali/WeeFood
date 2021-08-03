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
import androidx.compose.ui.ExperimentalComposeUiApi
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
import kotlinx.coroutines.ExperimentalCoroutinesApi


@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@Composable
fun NewRecipeScreen(
    navController: NavController
) {
    AppTheme() {
        Scaffold(
            topBar = { TopBar(title = "Neues Rezept") },
            bottomBar = { BottomBar(navController) }
        ) {
            LazyColumn() {
                item {

                    Text("Bild")
                    IngredientUnitTextField(value = "Name", label = "Rezeptname")
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
                        IngredientUnitTextField(value = "20", label = "Zeit")
                        IngredientUnitTextField(value = "min", label = "Einheit")
                    }
                    Text("Rezept")
                    IngredientUnitTextField(value = "Gaaaaaanz viel Text", label = "Rezept")
                }
            }
        }

    }
}




@ExperimentalFoundationApi
@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Preview(showBackground = true)
@Composable
fun NewRecipeScreenPreview() {

    val navController = rememberNavController()
    AppTheme() {
        NewRecipeScreen(navController)
    }
}