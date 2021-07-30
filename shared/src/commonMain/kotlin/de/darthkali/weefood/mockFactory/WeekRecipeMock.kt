package de.darthkali.weefood.mockFactory

import de.darthkali.weefood.domain.model.WeekRecipe
import de.darthkali.weefood.domain.util.enums.Weekday

object WeekRecipeMock {

    val weekRecipe = WeekRecipe(weekday = Weekday.MONDAY, portion = 1, recipe_id = 1)
    val weekDayForSearch = Weekday.MONDAY //Weekday.MONDAY

    val weekRecipeList = listOf(
        WeekRecipe(weekday = weekDayForSearch, portion = 2, recipe_id = 1),
        WeekRecipe(weekday = weekDayForSearch, portion = 4, recipe_id = 2),
        WeekRecipe(weekday = Weekday.THURSDAY, portion = 1, recipe_id = 3),
        WeekRecipe(weekday = Weekday.THURSDAY, portion = 3, recipe_id = 4)
    )
}