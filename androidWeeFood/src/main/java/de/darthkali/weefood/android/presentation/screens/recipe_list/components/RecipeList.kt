package de.darthkali.weefood.android.presentation.screens.recipe_list.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import de.darthkali.weefood.android.presentation.components.NothingHere
import de.darthkali.weefood.datasource.network.IngredientServiceImpl.Companion.PAGINATION_PAGE_SIZE
import de.darthkali.weefood.datasource.database.model.IngredientDb
import de.darthkali.weefood.domain.model.Ingredient
import kotlinx.coroutines.ExperimentalCoroutinesApi


@ExperimentalMaterialApi
@ExperimentalCoroutinesApi
@Composable
fun RecipeList(
    loading: Boolean,
    ingredientDbs: List<Ingredient>,
    page: Int,
    onTriggerNextPage: () -> Unit,
    onClickRecipeListItem: (Int) -> Unit,
){
    Box(modifier = Modifier
        .background(color = MaterialTheme.colors.surface)
    ) {
        if (loading && ingredientDbs.isEmpty()) {
            LoadingRecipeListShimmer(imageHeight = 250.dp,)
        }
        else if(ingredientDbs.isEmpty()){
            NothingHere()
        }
        else {
            LazyColumn{
                itemsIndexed(
                    items = ingredientDbs
                ) { index, recipe ->
                    if ((index + 1) >= (page * PAGINATION_PAGE_SIZE) && !loading) {
                        onTriggerNextPage()
                    }
                    RecipeCard(
                        ingredient = recipe,
                        onClick = {
                            onClickRecipeListItem(0) //TODO: replace 0
                        }
                    )
                }
            }
        }
    }
}
