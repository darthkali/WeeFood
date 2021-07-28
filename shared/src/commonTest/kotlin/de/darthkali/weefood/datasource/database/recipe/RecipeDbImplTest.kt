package de.darthkali.weefood.datasource.database.recipe

import de.darthkali.weefood.BaseTest
import de.darthkali.weefood.datasource.database.DatabaseHelper
import de.darthkali.weefood.domain.model.Ingredient
import de.darthkali.weefood.testDbConnection
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertTrue

class SqlDelightTest : BaseTest() {

    private lateinit var dbHelper: DatabaseHelper

    private suspend fun DatabaseHelper.insertIngredient(name: String) {
        insertIngredients(listOf(Ingredient(id = 1, name = name, "no.jpg")))
    }

    @BeforeTest
    fun setup() = runTest {
        dbHelper = DatabaseHelper(testDbConnection())
        dbHelper.deleteAll()
        dbHelper.insertIngredient("Beagle")
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


    @Test
    fun `Delete All Success`() = runTest {
        dbHelper.insertIngredient("Poodle")
        dbHelper.insertIngredient("Schnauzer")
        assertTrue(dbHelper.selectAllItems().isNotEmpty())
        dbHelper.deleteAll()

        assertTrue(
            dbHelper.selectAllItems().isEmpty(),
            "Delete All did not work"
        )
    }
}