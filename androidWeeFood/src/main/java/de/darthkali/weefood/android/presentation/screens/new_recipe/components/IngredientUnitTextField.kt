package de.darthkali.weefood.android.presentation.screens.recipe_detail.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@ExperimentalComposeUiApi
@Composable
fun IngredientUnitTextField(
    input: String = "",
    onInputChanged: (String) -> Unit,
    label: String
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    TextField(
        modifier = Modifier
            .width(120.dp)
            .padding(8.dp),
        value = input,
        onValueChange = { onInputChanged(it) },
        label = { Text(text = label) },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done,
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                //onExecuteSearch()
                keyboardController?.hide()
            },
        ),
        textStyle = TextStyle(color = MaterialTheme.colors.primary),
        colors = TextFieldDefaults.textFieldColors(backgroundColor = MaterialTheme.colors.surface),
    )

}

//@ExperimentalCoroutinesApi
//@ExperimentalMaterialApi
//@ExperimentalComposeUiApi
//@Preview(showBackground = true)
//@Composable
//fun IngredientUnitTextFieldPreview() {
//    AppTheme() {
//        val textState = remember { mutableStateOf(TextFieldValue()) }
//        IngredientUnitTextField(
//            value = "Test",
//            label = "Menge")
//    }
//
//}

