package de.darthkali.weefood.android.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.google.accompanist.coil.rememberCoilPainter
import com.google.accompanist.imageloading.ImageLoadState
import de.darthkali.weefood.datasource.network.IngredientServiceImpl.Companion.IMAGE_URL_MEDIUM

const val RECIPE_IMAGE_HEIGHT = 260

@Composable
fun RectangleImage(
    url: String,
    contentDescription: String,
) {
    val painter = rememberCoilPainter("$IMAGE_URL_MEDIUM/$url")
    Card(
        modifier = Modifier
            .padding(16.dp)
            .size(64.dp),
        elevation = 4.dp
    )
    {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(RECIPE_IMAGE_HEIGHT.dp),
            painter = painter,
            contentDescription = contentDescription,
            contentScale = ContentScale.Crop,
        )
        when (painter.loadState) {
            is ImageLoadState.Loading -> {
                Card(
                    shape = CircleShape,
                    modifier = Modifier
                        .size(64.dp),
                    elevation = 4.dp,
                ) {
                    // empty for white background
                    //TODO: insert loading image
                }
            }
            is ImageLoadState.Error -> {
                // If you wish to display some content if the request fails
            }
        }
    }
}