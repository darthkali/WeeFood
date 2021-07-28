package de.darthkali.weefood.datasource.database

import com.squareup.sqldelight.db.SqlDriver
import de.darthkali.weefood.domain.model.Ingredient
import de.darthkali.weefood.domain.model.Recipe
import de.darthkali.weefood.domain.model.RecipeIngredient
import de.darthkali.weefood.domain.model.WeekRecipe

class WeeFoodDatabaseFactory(
    private val driverFactory: DriverFactory
){
    fun createDatabase(): WeeFoodDatabase {
        return WeeFoodDatabase(driverFactory.createDriver())
    }
}

expect class DriverFactory {
    fun createDriver(): SqlDriver
}

/*
-- -----------------------------------------------------
-- ingredient_Entity
-- -----------------------------------------------------
    id      INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    name    TEXT    NOT NULL,
    image   TEXT    NOT NULL

//        aisle = featured_image,
//        possibleUnits = ingredients.convertIngredientsToList(),
-- -----------------------------------------------------
*/

fun Ingredient_Entity.toIngredient(): Ingredient {
    return Ingredient(
        id = id.toInt(),
        name = name,
        image = image,
    )
}


fun List<Ingredient_Entity>.toIngredientList(): List<Ingredient>{
    return map{it.toIngredient()}
}






/*
-- -----------------------------------------------------
-- recipe_Entity
-- -----------------------------------------------------
  id            INTEGER             NOT NULL PRIMARY KEY AUTOINCREMENT,
  name          TEXT                NOT NULL,
  image         TEXT,
  cooking_time  INTEGER AS Integer  NOT NULL,
  unit          TEXT                NOT NULL,
  description   TEXT
-- -----------------------------------------------------
*/

fun Recipe_Entity.toRecipe(): Recipe {
    return Recipe(
        id = id.toInt(),
        name = name,
        image = image,
        cooking_time = cooking_time,
        unit = unit,
        description = description
    )
}

fun List<Recipe_Entity>.toRecipeList(): List<Recipe>{
    return map{it.toRecipe()}
}







/*
-- -----------------------------------------------------
-- recipeIngredient_Entity
-- -----------------------------------------------------
  id            INTEGER             NOT NULL PRIMARY KEY AUTOINCREMENT,
  quantity      REAL AS Float       NOT NULL,
  unit          TEXT                NOT NULL,
  recipe_id     INTEGER AS Integer  NOT NULL,
  ingredient_id INTEGER AS Integer  NOT NULL,

  FOREIGN KEY (ingredient_id)   REFERENCES ingredient_Entity(id),
  FOREIGN KEY (recipe_id)       REFERENCES recipe_Entity(id)
-- -----------------------------------------------------
*/

fun RecipeIngredient_Entity.toRecipeIngredient(): RecipeIngredient {
    return RecipeIngredient(
        id = id.toInt(),
        quantity = quantity.toInt(),
        unit = unit.toInt(),
        recipe_id = recipe_id,
        ingredient_id = ingredient_id,
    )
}

fun List<RecipeIngredient_Entity>.toRecipeIngredientList(): List<RecipeIngredient>{
    return map{it.toRecipeIngredient()}
}




/*
-- -----------------------------------------------------
-- weekRecipe_Entity
-- -----------------------------------------------------
  id        INTEGER             NOT NULL PRIMARY KEY AUTOINCREMENT,
  weekday   INTEGER AS Integer  NOT NULL,
  portion   INTEGER AS Integer  NOT NULL,
  recipe_id INTEGER AS Integer  NOT NULL,

  FOREIGN KEY (recipe_id) REFERENCES recipe_Entity(id)
-- -----------------------------------------------------
*/

fun WeekRecipe_Entity.toWeekRecipe(): WeekRecipe {
    return WeekRecipe(
        id = id.toInt(),
        weekday = weekday,
        portion = portion,
        recipe_id = recipe_id,
    )
}

fun List<WeekRecipe_Entity>.toWeekRecipeList(): List<WeekRecipe>{
    return map{it.toWeekRecipe()}
}




/*
-- -----------------------------------------------------
-- Helper Methods
-- -----------------------------------------------------

*/
fun String.convertIngredientsToList(): List<String>{
    val list: ArrayList<String> = ArrayList()
    for(ingredient in split(",")){
        list.add(ingredient)
    }
    return list
}


/**
 * "Carrot, potato, Chicken, ..."
 */
fun List<String>.convertListToString(): String {
    val listString = StringBuilder()
    for(item in this){
        listString.append("$item,")
    }
    return listString.toString()
}