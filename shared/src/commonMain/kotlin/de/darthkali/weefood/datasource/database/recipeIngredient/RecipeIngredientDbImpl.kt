package de.darthkali.weefood.datasource.database.recipeIngredient

import de.darthkali.weefood.datasource.database.WeeFoodDatabase
import de.darthkali.weefood.datasource.database.WeeFoodDatabaseFactory
import de.darthkali.weefood.datasource.database.toRecipeIngredientList
import de.darthkali.weefood.domain.model.RecipeIngredient
import de.darthkali.weefood.util.Logger
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class RecipeIngredientDbImpl: RecipeIngredientDb , KoinComponent {

    private val weeFoodDatabaseFactory: WeeFoodDatabaseFactory by inject()
    private val weeFoodDatabase = weeFoodDatabaseFactory.createDatabase()
    private val logger = Logger("RecipeIngredientDbImpl")

    override fun insertRecipeIngredient(recipeIngredient: RecipeIngredient): Boolean {
        return try {
            weeFoodDatabase.recipeIngredientDbQueries.insertRecipeIngredient(
                null,
                quantity = recipeIngredient.quantity,
                unit = recipeIngredient.unit,
                recipe_id = recipeIngredient.recipe_id,
                ingredient_id = recipeIngredient.ingredient_id,
            )
            logger.log("Inserting with RecipeId: ${recipeIngredient.recipe_id} into database")
            true
        } catch (e: Exception) {
            logger.log(e.toString())
            false
        }
    }

    override fun getAllRecipeIngredientByRecipeId(recipeId: Int): List<RecipeIngredient> {
        return try {
            logger.log("Get RecipeIngredient from database by ID")
            weeFoodDatabase.recipeIngredientDbQueries.getAllRecipeIngredientsByRecipeId(
                recipe_id = recipeId
            ).executeAsList().toRecipeIngredientList()
        } catch (e: Exception) {
            logger.log(e.toString())
            listOf()
        }
    }

    override fun getAllRecipeIngredients(): List<RecipeIngredient> {
        return try {
            logger.log("Get all RecipeIngredients from database")
            weeFoodDatabase.recipeIngredientDbQueries.getAllRecipeIngredients()
                .executeAsList().toRecipeIngredientList()
        } catch (e: Exception) {
            logger.log(e.toString())
            listOf()
        }
    }

    override fun deleteRecipeIngredientById(recipeId: Int): Boolean {
        return try {
            logger.log("Delete RecipeIngredient from database by ID")
            weeFoodDatabase.recipeIngredientDbQueries.deleteRecipeIngredientById(id = recipeId.toLong())
            true
        } catch (e: Exception) {
            logger.log(e.toString())
            false
        }
    }

    override fun deleteAllRecipeIngredients(): Boolean {
        return try {
            logger.log("Delete all RecipeIngredients from database")
            weeFoodDatabase.recipeIngredientDbQueries.deleteAllRecipeIngredients()
            true
        } catch (e: Exception) {
            logger.log(e.toString())
            false
        }
    }
}