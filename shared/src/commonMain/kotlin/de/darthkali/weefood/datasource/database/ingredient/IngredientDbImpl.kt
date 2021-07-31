package de.darthkali.weefood.datasource.database.ingredient

import de.darthkali.weefood.datasource.database.WeeFoodDatabase
import de.darthkali.weefood.datasource.database.toIngredient
import de.darthkali.weefood.datasource.database.toIngredientList
import de.darthkali.weefood.domain.model.Ingredient
import de.darthkali.weefood.util.Logger

class IngredientDbImpl(
    var weeFoodDatabase: WeeFoodDatabase
) : IngredientDb {

    private val logger = Logger("IngredientDbImpl")

    override fun insertIngredient(ingredient: Ingredient): Boolean {

        return try {
            weeFoodDatabase.ingredientDbQueries.insertIngredient(
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
            weeFoodDatabase.ingredientDbQueries.getAllIngredients(
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
            weeFoodDatabase.ingredientDbQueries.getIngredientById(
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
            weeFoodDatabase.ingredientDbQueries.deleteIngredientById(id = ingredientId.toLong())
            true
        } catch (e: Exception) {
            logger.log(e.toString())
            false
        }
    }

    override fun deleteAllIngredients(): Boolean {
        return try {
            logger.log("Delete all Ingredients from database")
            weeFoodDatabase.ingredientDbQueries.deleteAllIngredients()
            true
        } catch (e: Exception) {
            logger.log(e.toString())
            false
        }
    }
}
