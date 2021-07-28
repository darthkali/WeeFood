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


        val ingredients = listOf<Ingredient>(
            Ingredient(id = 1, name = "name1", "no.jpg"),
            Ingredient(id = 2, name = "name2", "no.jpg"),
            Ingredient(id = 3, name = "name3", "no.jpg")
        )


        for (ingredient in ingredients) {
            ingredientDb.insertIngredient(ingredient)
        }
    }

    @Test
    fun `Delete All Success`() = runTest {
        assertTrue(ingredientDb.getAllIngredients().isNotEmpty())
        ingredientDb.deleteAllIngredients()

        assertTrue(
            ingredientDb.getAllIngredients().isEmpty(),
            "Delete All did not work"
        )
    }

//    @Test
//    fun `Select All Items Success`() = runTest {
//        val breeds = dbHelper.selectAllItems().first()
//        assertNotNull(
//            breeds.find { it.name == "Beagle" },
//            "Could not retrieve Breed"
//        )
//    }

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