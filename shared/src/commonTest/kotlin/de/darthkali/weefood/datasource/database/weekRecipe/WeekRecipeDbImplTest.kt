package de.darthkali.weefood.datasource.database.weekRecipe

import de.darthkali.weefood.BaseTest
import de.darthkali.weefood.datasource.database.WeeFoodDatabase
import de.darthkali.weefood.mockFactory.WeekRecipeMock
import de.darthkali.weefood.testDbConnection
import de.darthkali.weefood.writeHead
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class WeekRecipeDbImplTest : BaseTest() {

    private val weeFoodDatabase: WeeFoodDatabase = WeeFoodDatabase(testDbConnection())
    private val weekRecipeDb: WeekRecipeDb by lazy {
        WeekRecipeDbImpl(
            weeFoodDatabase = weeFoodDatabase
        )
    }


    @BeforeTest
    fun setup() = runTest {
        writeHead("setup")
        weekRecipeDb.deleteAllWeekRecipe()
        val weekRecipes = WeekRecipeMock.weekRecipeList

        for (weekRecipe in weekRecipes) {
            weekRecipeDb.insertWeekRecipe(weekRecipe)
        }
    }


    @Test
    fun get_all_week_recipes_success() = runTest {
        writeHead("get_all_week_recipes_success")
        val weekRecipes = weekRecipeDb.getAllWeekRecipes()
        weekRecipes.forEachIndexed { index, weekRecipe ->
            println(weekRecipe.toString())

            assertEquals(
                expected = WeekRecipeMock.weekRecipeList[index],
                actual = weekRecipe
            )
        }
    }

    @Test
    fun get_all_week_recipe_by_weekday() = runTest {
        writeHead("get_all_week_recipe_by_weekday")

        val weekRecipes = weekRecipeDb.getAllWeekRecipesByWeekDay(WeekRecipeMock.weekDayForSearch)

        for (weekRecipe in weekRecipes) {
            println(weekRecipe.toString())
            assertEquals(
                expected = WeekRecipeMock.weekDayForSearch,
                actual = weekRecipe.weekday,
            )
        }
    }


    @Test
    fun delete_all_week_recipes_success() = runTest {
        writeHead("delete_all_week_recipes_success")
        assertTrue(weekRecipeDb.getAllWeekRecipes().isNotEmpty())
        weekRecipeDb.deleteAllWeekRecipe()

        assertTrue(
            weekRecipeDb.getAllWeekRecipes().isEmpty(),
            "Delete All did not work"
        )
    }


    @Test
    fun delete_week_recipe_by_id_success() = runTest {
        writeHead("delete_week_recipe_by_id_success")

        weekRecipeDb.getAllWeekRecipes().forEachIndexed { index, weekRecipe ->

            val weekRecipeId = weekRecipe.id
            println("Delete Ingredient with ID: $weekRecipeId")
            weekRecipeDb.deleteWeekRecipeById(weekRecipeId)

            assertEquals(
                expected = weekRecipeDb.getAllWeekRecipes().size,
                actual = WeekRecipeMock.weekRecipeList.size - (index + 1),
            )
        }
        assertEquals(
            expected = 0,
            actual = weekRecipeDb.getAllWeekRecipes().size,
        )
    }

    @Test
    fun insert_week_recipe_success() = runTest {
        writeHead("insert_week_recipe_success")

        for (weekRecipe in weekRecipeDb.getAllWeekRecipes()) {
            println(weekRecipe.toString())
        }

        weekRecipeDb.insertWeekRecipe(WeekRecipeMock.weekRecipe)

        for (weekRecipe in weekRecipeDb.getAllWeekRecipes()) {
            println(weekRecipe.toString())
        }

        assertEquals(
            expected = weekRecipeDb.getAllWeekRecipes().last(),
            actual = WeekRecipeMock.weekRecipe,
        )
    }
}