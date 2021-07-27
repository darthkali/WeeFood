package de.darthkali.weefood.android.presentation.screens.recipe_list.components


import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FoodCategoryChip(
    category: String,
    isSelected: Boolean = false,
    onSelectedCategoryChanged: (String) -> Unit,
){
    Surface(
        modifier = Modifier.padding(start = 4.dp, end = 4.dp),
        elevation = 8.dp,
        shape = MaterialTheme.shapes.large,
        color = MaterialTheme.colors.primaryVariant
    ) {
        Row(modifier = Modifier
            .toggleable(
                value = isSelected,
                onValueChange = {
                    onSelectedCategoryChanged(category)
                }
            )
        ) {
            Text(
                text = category,
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.onBackground,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}