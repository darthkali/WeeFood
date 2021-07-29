package de.darthkali.weefood.datasource.database.ingredient

import de.darthkali.weefood.BaseTest
import de.darthkali.weefood.datasource.database.DriverFactory
import de.darthkali.weefood.datasource.database.RecipeCache
import de.darthkali.weefood.datasource.database.RecipeCacheImpl
import de.darthkali.weefood.datasource.database.WeeFoodDatabaseFactory
import de.darthkali.weefood.datasource.database.WeeFoodDatabase
import de.darthkali.weefood.domain.model.Ingredient
import de.darthkali.weefood.domain.util.DatetimeUtil
import de.darthkali.weefood.testDbConnection
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertTrue

class SqlDelightTest : BaseTest() {

    val weeFoodDatabase: WeeFoodDatabase = WeeFoodDatabase(testDbConnection())
    val ingredientDb: IngredientDb by lazy {
        IngredientDbImpl(
            weeFoodDatabase = weeFoodDatabase
        )
    }


    @BeforeTest
    fun setup() = runTest {

        ingredientDb.deleteAllIngredients()

//        val ingredients =
        val ingredients = listOf<Ingredient>(
            Ingredient(id = null, name = "name1", "no.jpg"),
            Ingredient(id = null, name = "name2", "no.jpg"),
            Ingredient(id = null, name = "name3", "no.jpg")
        )


        for (ingredient in ingredients) {
            ingredientDb.insertIngredient(ingredient)
        }
    }



    @Test
    fun delete_all_ingredients_success() = runTest {
        assertTrue(ingredientDb.getAllIngredients().isNotEmpty())
        ingredientDb.deleteAllIngredients()

        assertTrue(
            ingredientDb.getAllIngredients().isEmpty(),
            "Delete All did not work"
        )
    }

    @Test
    fun get_all_ingredients_success() = runTest {
        val ingredients = ingredientDb.getAllIngredients()
//        assertNotNull(
//            breeds.find { it.name == "Beagle" },
//            "Could not retrieve Breed"
//        )
        for(ingredient in ingredients){
            println(ingredient.id)
        }


    }

//    @Test
//    fun `Select Item by Id Success`() = runTest {
//        val breeds = dbHelper.selectAllItems().first()
//        val firstBreed = breeds.first()
//        assertNotNull(
//            dbHelper.selectById(firstBreed.id),
//            "Could not retrieve Breed by Id"
//        )
//    }

//    @Test
//    fun `Update Favorite Success`() = runTest {
//        val breeds = dbHelper.selectAllItems().first()
//        val firstBreed = breeds.first()
//        dbHelper.updateFavorite(firstBreed.id, true)
//        val newBreed = dbHelper.selectById(firstBreed.id).first().first()
//        assertNotNull(
//            newBreed,
//            "Could not retrieve Breed by Id"
//        )
//        assertTrue(
//            newBreed.isFavorited(),
//            "Favorite Did Not Save"
//        )
//    }


}