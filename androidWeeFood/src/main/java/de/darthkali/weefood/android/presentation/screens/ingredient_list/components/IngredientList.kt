package de.darthkali.weefood.android.presentation.screens.ingredient_list.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import de.darthkali.weefood.android.presentation.components.NothingHere
import de.darthkali.weefood.datasource.network.IngredientServiceImpl.Companion.RECIPE_PAGINATION_PAGE_SIZE
import de.darthkali.weefood.domain.model.Ingredient
import kotlinx.coroutines.ExperimentalCoroutinesApi


@ExperimentalMaterialApi
@ExperimentalCoroutinesApi
@Composable
fun IngredientList(
    loading: Boolean = false,
    ingredients: List<Ingredient> = listOf(),
    page: Int = 1,
    onTriggerNextPage: () -> Unit,
    onClickRecipeListItem: (Int) -> Unit,
){
    Box(modifier = Modifier
        .background(color = MaterialTheme.colors.surface)
    ) {
        if (loading && ingredients.isEmpty()) {
            LoadingIngredientListShimmer(imageHeight = 250.dp,)
        }
        else if(ingredients.isEmpty()){
            NothingHere()
        }
        else {
            LazyColumn{
                itemsIndexed(
                    items = ingredients
                ) { index, recipe ->
                    if ((index + 1) >= (page * RECIPE_PAGINATION_PAGE_SIZE) && !loading) {
                        onTriggerNextPage()
                    }
                    IngredientCard(ingredient = recipe)
                }
            }
        }
    }
}


