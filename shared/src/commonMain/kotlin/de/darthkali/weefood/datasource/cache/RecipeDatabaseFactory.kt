package de.darthkali.weefood.datasource.cache

import com.squareup.sqldelight.db.SqlDriver
import de.darthkali.weefood.domain.model.Recipe
import de.darthkali.weefood.domain.util.DatetimeUtil

class RecipeDatabaseFactory(
    private val driverFactory: DriverFactory
){
    fun createDatabase(): RecipeDatabase {
        return RecipeDatabase(driverFactory.createDriver())
    }
}

expect class DriverFactory {
    fun createDriver(): SqlDriver
}

fun Recipe_Entity.toRecipe(): Recipe {
    return Recipe(
        id = id.toInt(),
        name = title,
        image = publisher,
        aisle = featured_image,
        possibleUnits = ingredients.convertIngredientsToList(),
    )
}

fun List<Recipe_Entity>.toRecipeList(): List<Recipe>{
    return map{it.toRecipe()}
}

/**
 * "Carrot, potato, Chicken, ..."
 */
fun List<String>.convertIngredientListToString(): String {
    val ingredientsString = StringBuilder()
    for(ingredient in this){
        ingredientsString.append("$ingredient,")
    }
    return ingredientsString.toString()
}

fun String.convertIngredientsToList(): List<String>{
    val list: ArrayList<String> = ArrayList()
    for(ingredient in split(",")){
        list.add(ingredient)
    }
    return list
}