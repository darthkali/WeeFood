package de.darthkali.weefood.android.presentation.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun Button(
    text: String = "",
){
    Text(text)

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Button()
}
