package de.darthkali.weefood.datasource.database.ingredient

import de.darthkali.weefood.datasource.database.Ingredient_Entity
import de.darthkali.weefood.datasource.database.WeeFoodDatabaseWrapper
import de.darthkali.weefood.domain.model.Ingredient
import de.darthkali.weefood.util.Logger
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class IngredientDbImpl : IngredientDb, KoinComponent {

    private val weeFoodDatabase: WeeFoodDatabaseWrapper by inject()
    private val weeFoodDatabaseQueries = weeFoodDatabase.instance.ingredientDbQueries

    private val logger = Logger("IngredientDbImpl")


    override fun insertIngredient(ingredient: Ingredient): Boolean {
        return try {
            weeFoodDatabaseQueries.insertIngredient(
                null,
                name = ingredient.name ?: "",
                image = ingredient.image ?: "no.jpg",
                name_ = ingredient.name ?: ""           // checkAttribute to avoid duplicates in DB
            )
            logger.log("Inserting ${ingredient.name} into database")
            true
        } catch (e: Exception) {
            logger.log(e.toString())
            false
        }
    }

    override fun getAllIngredients(): List<Ingredient> {
        return try {
            logger.log("Get All Ingredients from database")
            weeFoodDatabaseQueries.getAllIngredients(
                pageSize = 100,  // TODO replace with parameter
                offset = 0       // TODO replace with parameter
            ).executeAsList().toIngredientList()

        } catch (e: Exception) {
            logger.log(e.toString())
            listOf()
        }
    }

    override fun getIngredientById(ingredientId: Int): Ingredient? {
        return try {
            logger.log("Get Ingredient from database by ID")
            weeFoodDatabaseQueries.getIngredientById(
                id = ingredientId.toLong()
            ).executeAsOne().toIngredient()
        } catch (e: Exception) {
            logger.log(e.toString())
            null
        }
    }

    override fun deleteIngredientById(ingredientId: Int): Boolean {
        return try {
            logger.log("Delete Ingredient from database by ID")
            weeFoodDatabaseQueries.deleteIngredientById(id = ingredientId.toLong())
            true
        } catch (e: Exception) {
            logger.log(e.toString())
            false
        }
    }

    override fun deleteAllIngredients(): Boolean {
        return try {
            logger.log("Delete all Ingredients from database")
            weeFoodDatabaseQueries.deleteAllIngredients()
            true
        } catch (e: Exception) {
            logger.log(e.toString())
            false
        }
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


    fun List<Ingredient_Entity>.toIngredientList(): List<Ingredient> {
        return map { it.toIngredient() }
    }

}
