package de.darthkali.weefood.datasource.database.queries.ingredient

import de.darthkali.weefood.datasource.database.Ingredient_Entity
import de.darthkali.weefood.datasource.database.WeeFoodDatabaseWrapper
import de.darthkali.weefood.datasource.database.mapper.ingredient.IngredientMapper
import de.darthkali.weefood.datasource.database.model.IngredientDb
import de.darthkali.weefood.datasource.network.mapper.IngredientListMapper
import de.darthkali.weefood.util.Logger
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class IngredientQueriesImpl : IngredientQueries, KoinComponent {

    private val weeFoodDatabase: WeeFoodDatabaseWrapper by inject()
    private val weeFoodDatabaseQueries = weeFoodDatabase.instance.ingredientDbQueries
    private val mapper = IngredientMapper()
    private val mapperList = IngredientListMapper()

    private val logger = Logger("IngredientQueriesImpl")


    override fun insertIngredient(ingredientDb: IngredientDb): Int? {
        return try {
            weeFoodDatabaseQueries.insertIngredient(
                null,
                apiId = ingredientDb.apiId.toLong(),
                name = ingredientDb.name ?: "",
                image = ingredientDb.image ?: "no.jpg",
            )
            logger.log("Inserting ${ingredientDb.name} into database")
            weeFoodDatabaseQueries.getLastInsertRowId().executeAsOne().toInt()
        } catch (e: Exception) {
            logger.log(e.toString())
            null
        }
    }

    override fun updateIngredientByApiId(ingredientDb: IngredientDb): Int? {
        return try {
            weeFoodDatabaseQueries.updateIngredient(
                name = ingredientDb.name ?: "",
                image = ingredientDb.image ?: "no.jpg",
                apiId = ingredientDb.apiId.toLong(),
            )
            logger.log("Updated ${ingredientDb.name} in database")
            weeFoodDatabaseQueries.getIngredientByApiId(ingredientDb.apiId.toLong())
                .executeAsOne().id.toInt()
        } catch (e: Exception) {
            logger.log(e.toString())
            null
        }
    }

    override fun getAllIngredients(): List<IngredientDb> {
        return try {
            logger.log("Get All Ingredients from database")
            weeFoodDatabaseQueries.getAllIngredients(
                pageSize = 100,  // TODO replace with parameter
                offset = 0       // TODO replace with parameter
            ).executeAsList().toIngredientDbList()

        } catch (e: Exception) {
            logger.log(e.toString())
            listOf()
        }
    }

    override fun getIngredientById(ingredientId: Int): IngredientDb? {
        return try {
            logger.log("Get Ingredient from database by ID")
            weeFoodDatabaseQueries.getIngredientById(
                id = ingredientId.toLong()
            ).executeAsOne().toIngredientDb()
        } catch (e: Exception) {
            logger.log(e.toString())
            null
        }
    }

    override fun getIngredientByApiId(apiId: Int): IngredientDb? {
        return try {
            logger.log("Get Ingredient from database by ApiID")
            weeFoodDatabaseQueries.getIngredientByApiId(
                apiId = apiId.toLong()
            ).executeAsOne().toIngredientDb()
        } catch (e: Exception) {
            logger.log(e.toString())
            null
        }
    }

    override fun getLastInsertRowId(): Int? {
        return try {
            logger.log("Get last insert row id")
            weeFoodDatabaseQueries.getLastInsertRowId().executeAsOne().toInt()
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

    fun Ingredient_Entity.toIngredientDb(): IngredientDb {
        return IngredientDb(
            name = name,
            image = image,
            apiId = apiId.toInt(),
            id = id.toInt(),
        )
    }


    fun List<Ingredient_Entity>.toIngredientDbList(): List<IngredientDb> {
        return map { it.toIngredientDb() }
    }

}
