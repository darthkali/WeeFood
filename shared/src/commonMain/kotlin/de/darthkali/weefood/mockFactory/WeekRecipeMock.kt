package de.darthkali.weefood.mockFactory

import de.darthkali.weefood.datasource.database.model.WeekRecipeDb
import de.darthkali.weefood.domain.util.enums.Weekday

object WeekRecipeMock {

    val weekDayForSearch = Weekday.MONDAY // Weekday.MONDAY

    val weekRecipeDb = WeekRecipeDb(weekday = Weekday.MONDAY, portion = 1, recipe_id = 1)

    val weekRecipeDbList = listOf(
        WeekRecipeDb(weekday = weekDayForSearch, portion = 2, recipe_id = 1),
        WeekRecipeDb(weekday = weekDayForSearch, portion = 4, recipe_id = 2),
        WeekRecipeDb(weekday = Weekday.THURSDAY, portion = 1, recipe_id = 3),
        WeekRecipeDb(weekday = Weekday.THURSDAY, portion = 3, recipe_id = 4)
    )
}
