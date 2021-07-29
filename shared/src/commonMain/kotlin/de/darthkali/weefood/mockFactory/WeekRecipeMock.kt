package de.darthkali.weefood.mockFactory

import de.darthkali.weefood.domain.model.WeekRecipe
import de.darthkali.weefood.domain.util.Weekday

object WeekRecipeMock {

    val weekRecipe = WeekRecipe(weekday = Weekday.MONTAG, portion = 2, recipe_id = 1)

    val weekRecipeList = listOf(
        WeekRecipe(weekday = Weekday.MONTAG, portion = 2, recipe_id = 1),
        WeekRecipe(weekday = Weekday.MONTAG, portion = 4, recipe_id = 2),
        WeekRecipe(weekday = Weekday.DONNERSTAG, portion = 1, recipe_id = 3),
        WeekRecipe(weekday = Weekday.DONNERSTAG, portion = 3, recipe_id = 4)
    )
}