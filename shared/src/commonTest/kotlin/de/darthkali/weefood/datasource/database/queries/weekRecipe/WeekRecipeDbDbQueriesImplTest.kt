package de.darthkali.weefood.datasource.database.weekRecipe

import de.darthkali.weefood.BaseTest
import de.darthkali.weefood.datasource.database.queries.weekRecipe.WeekRecipeQueries
import de.darthkali.weefood.mockFactory.WeekRecipeMock
import de.darthkali.weefood.writeHead
import org.koin.core.component.inject
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class WeekRecipeDbDbQueriesImplTest : BaseTest() {

    private val weekRecipeQueries: WeekRecipeQueries by inject()


    @BeforeTest
    fun setup() = runTest {
        writeHead("setup")
        weekRecipeQueries.deleteAllWeekRecipe()
        val weekRecipes = WeekRecipeMock.weekRecipeList

        for (weekRecipe in weekRecipes) {
            weekRecipeQueries.insertWeekRecipe(weekRecipe)
        }
    }


    @Test
    fun get_all_week_recipes_success() = runTest {
        writeHead("get_all_week_recipes_success")
        val weekRecipes = weekRecipeQueries.getAllWeekRecipes()
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

        val weekRecipes = weekRecipeQueries.getAllWeekRecipesByWeekDay(WeekRecipeMock.weekDayForSearch)

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
        assertTrue(weekRecipeQueries.getAllWeekRecipes().isNotEmpty())
        weekRecipeQueries.deleteAllWeekRecipe()

        assertTrue(
            weekRecipeQueries.getAllWeekRecipes().isEmpty(),
            "Delete All did not work"
        )
    }


    @Test
    fun delete_week_recipe_by_id_success() = runTest {
        writeHead("delete_week_recipe_by_id_success")

        weekRecipeQueries.getAllWeekRecipes().forEachIndexed { index, weekRecipe ->

            val weekRecipeId = weekRecipe.id
            println("Delete Ingredient with ID: $weekRecipeId")
            weekRecipeQueries.deleteWeekRecipeById(weekRecipeId)

            assertEquals(
                expected = weekRecipeQueries.getAllWeekRecipes().size,
                actual = WeekRecipeMock.weekRecipeList.size - (index + 1),
            )
        }
        assertEquals(
            expected = 0,
            actual = weekRecipeQueries.getAllWeekRecipes().size,
        )
    }

    @Test
    fun insert_week_recipe_success() = runTest {
        writeHead("insert_week_recipe_success")

        for (weekRecipe in weekRecipeQueries.getAllWeekRecipes()) {
            println(weekRecipe.toString())
        }

        weekRecipeQueries.insertWeekRecipe(WeekRecipeMock.weekRecipe)

        for (weekRecipe in weekRecipeQueries.getAllWeekRecipes()) {
            println(weekRecipe.toString())
        }

        assertEquals(
            expected = weekRecipeQueries.getAllWeekRecipes().last(),
            actual = WeekRecipeMock.weekRecipe,
        )
    }
}