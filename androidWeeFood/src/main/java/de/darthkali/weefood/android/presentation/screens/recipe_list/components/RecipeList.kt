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
import de.darthkali.weefood.datasource.database.model.RecipeDb
import de.darthkali.weefood.domain.model.Ingredient
import kotlinx.coroutines.ExperimentalCoroutinesApi


@ExperimentalMaterialApi
@ExperimentalCoroutinesApi
@Composable
fun RecipeList(
    loading: Boolean,
    recipeDbs: List<RecipeDb>,
    page: Int,
    onTriggerNextPage: () -> Unit,
    onClickRecipeListItem: (Int) -> Unit,
){
    Box(modifier = Modifier
        .background(color = MaterialTheme.colors.background)
    ) {
        if (loading && recipeDbs.isEmpty()) {
            LoadingRecipeListShimmer(imageHeight = 250.dp,)
        }
        else if(recipeDbs.isEmpty()){
            NothingHere()
        }
        else {
            LazyColumn{
                itemsIndexed(
                    items = recipeDbs
                ) { index, recipe ->
                    if ((index + 1) >= (page * PAGINATION_PAGE_SIZE) && !loading) {
                        onTriggerNextPage()
                    }
                    RecipeCard(
                        recipe = recipe,
                        onClick = {
                            onClickRecipeListItem(recipe.id) //TODO: replace 0
                        }
                    )
                }
            }
        }
    }
}
