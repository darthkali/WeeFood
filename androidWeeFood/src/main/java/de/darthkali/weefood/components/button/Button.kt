package de.darthkali.weefood.components.button

import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import de.darthkali.weefood.theme.AppTheme
import java.util.Locale
import kotlinx.coroutines.ExperimentalCoroutinesApi

@Composable
fun CommonButton(
    modifier: Modifier = Modifier,
    text: String = "",
    buttonStyle: ButtonStyle = ButtonStyle.OPEN_BUTTON,
    onClick: () -> Unit
) {
    when (buttonStyle) {
        ButtonStyle.OPEN_BUTTON ->
            Button(
                onClick = onClick,
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary),
                modifier = modifier
            ) {
                ButtonText(text = text, color = MaterialTheme.colors.onPrimary)
            }

        ButtonStyle.ADD_BUTTON ->
            Button(
                onClick = onClick,
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primaryVariant),
                modifier = modifier
            ) {
                ButtonText(text = text, color = MaterialTheme.colors.onBackground)
            }

        ButtonStyle.CLOSE_BUTTON ->
            TextButton(
                onClick = onClick,
                modifier = modifier
            ) {
                ButtonText(text = text, color = MaterialTheme.colors.onBackground)
            }

        ButtonStyle.DELETE_BUTTON ->
            Button(
                onClick = onClick,
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary),
                modifier = modifier
            ) {
                ButtonText(text = text, color = MaterialTheme.colors.onSecondary)
            }
    }
}

@Composable
fun ButtonText(
    text: String = "",
    color: Color = MaterialTheme.colors.onPrimary,
) {
    Text(
        color = color,
        style = MaterialTheme.typography.button,
        text = text.uppercase(Locale.getDefault())
    )
}


@Composable
fun MyFloatingActionButton(
    onClick: () -> Unit,
    color: Color = MaterialTheme.colors.primary,
) {
    FloatingActionButton(
        onClick = onClick,
        backgroundColor = color
    ) {
        Icon(Icons.Filled.Check, "")
    }
}


@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AppTheme {
        CommonButton(text = "Open", buttonStyle = ButtonStyle.OPEN_BUTTON) {}
        CommonButton(text = "Add", buttonStyle = ButtonStyle.ADD_BUTTON) {}
        CommonButton(text = "Delete", buttonStyle = ButtonStyle.DELETE_BUTTON) {}
        CommonButton(text = "Close ", buttonStyle = ButtonStyle.CLOSE_BUTTON) {}
        MyFloatingActionButton({})
    }

}
