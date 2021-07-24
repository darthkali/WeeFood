package de.darthkali.weefood.android.presentation.navigation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun TopBar(
    title: String = "",
    icon: ImageVector? = null,
    iconClickAction: (() -> Unit)? = null
) {
    if (icon == null) {
        TopAppBar(
            title = { Text(title) },
            backgroundColor = MaterialTheme.colors.primary,

        )
    }else{
        TopAppBar(
            navigationIcon = {
                Icon(
                    imageVector = icon,
                    contentDescription = "contentDescription",
                    modifier = Modifier
                        .padding(horizontal = 12.dp)
                        .clickable(onClick = { iconClickAction?.invoke() })
                )
            },
            title = { Text(title) }
        )
    }

}
