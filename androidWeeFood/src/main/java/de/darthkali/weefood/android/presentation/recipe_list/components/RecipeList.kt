package de.darthkali.weefood.android.presentation.recipe_list.components


import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import de.darthkali.weefood.android.presentation.components.RECIPE_IMAGE_HEIGHT
import de.darthkali.weefood.datasource.network.model.RecipeServiceImpl.Companion.RECIPE_PAGINATION_PAGE_SIZE
import de.darthkali.weefood.domain.model.Recipe


@Composable
fun RecipeList(
    loading: Boolean,
    recipes: List<Recipe>,
    page: Int,
    onTriggerNextPage: () -> Unit,
    onClickRecipeListItem: (Int) -> Unit
) {
    if (loading && recipes.isEmpty()) {
        LoadingRecipeListShimmer(imageHeight = RECIPE_IMAGE_HEIGHT.dp)
    } else if (recipes.isEmpty()) {
        //Nothing to show
    } else {
        LazyColumn {
            itemsIndexed(
                items = recipes
            ) { index, recipe ->
                if ((index + 1) >= (page * RECIPE_PAGINATION_PAGE_SIZE) && !loading) {
                    onTriggerNextPage()
                }
                RecipeCard(
                    recipe = recipe,
                    onClick = {
                        onClickRecipeListItem(recipe.id)
                    })
            }
        }
    }
}
