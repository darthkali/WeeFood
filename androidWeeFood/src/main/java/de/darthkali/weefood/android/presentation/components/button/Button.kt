package de.darthkali.weefood.android.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import de.darthkali.weefood.android.presentation.components.button.ButtonStyle
import de.darthkali.weefood.android.presentation.theme.AppTheme
import java.util.Locale
import kotlinx.coroutines.ExperimentalCoroutinesApi

@Composable
fun CommonButton(
    text: String = "",
    buttonStyle: ButtonStyle = ButtonStyle.OPEN_BUTTON,
    onClick: () -> Unit
){

    when(buttonStyle){
        ButtonStyle.OPEN_BUTTON ->
            Button(
                onClick = onClick,
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary),
            ){
                ButtonText(text = text, color = MaterialTheme.colors.onPrimary)
            }

        ButtonStyle.ADD_BUTTON ->
            Button(
                onClick = onClick,
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primaryVariant),
            ){
                ButtonText(text = text, color = MaterialTheme.colors.onBackground)
            }

        ButtonStyle.CLOSE_BUTTON ->
            TextButton(
                onClick = onClick,
            ){
                ButtonText(text = text, color = MaterialTheme.colors.onBackground)
            }

        ButtonStyle.DELETE_BUTTON ->
            Button(
                onClick = onClick,
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary),
            ){
                ButtonText(text = text, color = MaterialTheme.colors.onSecondary)
            }
    }
}

@Composable
fun ButtonText(
    text: String = "",
    color: Color = MaterialTheme.colors.onPrimary,
){
    Text(
        color = color,
        style = MaterialTheme.typography.button,
        text = text.uppercase(Locale.getDefault())
    )
}



@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AppTheme {
        CommonButton( text = "Open",  buttonStyle = ButtonStyle.OPEN_BUTTON, onClick = {})
        CommonButton( text = "Add",  buttonStyle = ButtonStyle.ADD_BUTTON, onClick = {})
        CommonButton( text = "Delete",  buttonStyle = ButtonStyle.DELETE_BUTTON, onClick = {})
        CommonButton( text = "Close ",  buttonStyle = ButtonStyle.CLOSE_BUTTON, onClick = {})
    }

}
