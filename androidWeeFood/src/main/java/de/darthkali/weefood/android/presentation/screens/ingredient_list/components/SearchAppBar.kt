package de.darthkali.weefood.android.presentation.screens.ingredient_list.components


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import de.darthkali.weefood.android.presentation.theme.AppTheme
import de.darthkali.weefood.domain.model.Ingredient
import de.darthkali.weefood.presentation.recipe_list.FoodCategory
import de.darthkali.weefood.presentation.recipe_list.FoodCategoryUtil
import kotlinx.coroutines.ExperimentalCoroutinesApi


@ExperimentalComposeUiApi
@Composable
fun SearchAppBar(
    query: String = "",
    onQueryChanged: (String) -> Unit,
    onExecuteSearch: () -> Unit,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        color = MaterialTheme.colors.background,
        elevation = 8.dp,
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                    ,
                    value = query,
                    onValueChange = { onQueryChanged(it) },
                    label = { Text(text = "Search") },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done,
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            onExecuteSearch()
                            keyboardController?.hide()
                        },
                    ),
                    leadingIcon = { Icon(Icons.Filled.Search, contentDescription = "Search Icon") },
                    textStyle = TextStyle(color = MaterialTheme.colors.primary),
                    colors = TextFieldDefaults.textFieldColors(backgroundColor = MaterialTheme.colors.surface),
                )
            }
        }
    }
}


@OptIn(
    ExperimentalCoroutinesApi::class,
    androidx.compose.ui.ExperimentalComposeUiApi::class,
    androidx.compose.material.ExperimentalMaterialApi::class
)
@Preview(showBackground = true)
@Composable
fun SearchAppBarPreview() {
    AppTheme() {
        SearchAppBar(
            query = "",
            onQueryChanged = { /*TODO*/ },
            onExecuteSearch = { /*TODO*/ },
        )
    }

}
