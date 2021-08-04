package de.darthkali.weefood.android.presentation.screens.recipe_detail.components


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import de.darthkali.weefood.android.presentation.components.CircleImage
import de.darthkali.weefood.datasource.database.model.IngredientDb
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalStdlibApi
@ExperimentalCoroutinesApi
@Composable
fun RecipeView(
    ingredientDb: IngredientDb,
){
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        item {
            CircleImage(
                url = ingredientDb.image,
                contentDescription = ingredientDb.name
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 4.dp)
                ){
                    Text(
                        text = ingredientDb.name ?: "",    //if ingredient.name == null, then set "" as text
                        modifier = Modifier
                            .fillMaxWidth(0.85f)
                            .wrapContentWidth(Alignment.Start)
                        ,
                        style = MaterialTheme.typography.h3
                    )
                    //val rank = recipe.rating.toString()
//                    Text(
//                        text = rank,
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .wrapContentWidth(Alignment.End)
//                            .align(Alignment.CenterVertically)
//                        ,
//                        style = MaterialTheme.typography.h5
//                    )
                }
//                val datetimeUtil = remember{DatetimeUtil()}
//                Text(
//                    text = "Updated ${datetimeUtil.humanizeDatetime(recipe.dateUpdated)} by ${recipe.publisher}"
//                    ,
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(bottom = 8.dp)
//                    ,
//                    style = MaterialTheme.typography.caption
//                )
//                for(ingredient in recipe.ingredients){
//                    Text(
//                        text = ingredient,
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(bottom = 4.dp)
//                        ,
//                        style = MaterialTheme.typography.body1
//                    )
//                }
            }
        }
    }
}