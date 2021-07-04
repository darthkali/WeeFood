package de.darthkali.weefood.android.presentation.recipe_detail


import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import de.darthkali.weefood.android.presentation.recipe_list.components.RecipeCard
import de.darthkali.weefood.android.presentation.theme.AppTheme
import de.darthkali.weefood.domain.model.Recipe

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
fun RecipeDetailScreen(
    recipe: Recipe?
) {
    AppTheme(displayProgressBar = false) {
        if(recipe == null){
            Text("ERROR")
        }else{

            RecipeCard(recipe = recipe, onClick = {})

        }
    }

}