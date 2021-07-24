package de.darthkali.weefood.android.presentation.navigation

import de.darthkali.weefood.android.R

sealed class NavigationItem(
    var route: String,
    var icon: Int? = null,
    var title: String = ""
) {
    //for Bottom Nav
    object WeekList : NavigationItem("weekList", R.drawable.ic_home, "Wochenplan")
    object Settings : NavigationItem("settings", R.drawable.ic_settings, "Settings")
    object RecipeList : NavigationItem("recipeList", R.drawable.ic_menu_book, "Rezepte")
    object ShoppingList : NavigationItem("shoppingList", R.drawable.ic_shopping_cart, "Einkaufsliste")

    // not in Bottom Nav
    object DayList : NavigationItem("dayList")
    object RecipeDetail : NavigationItem("recipeDetail")
    object NewRecipe : NavigationItem("newRecipe")
    object IngredientList : NavigationItem("ingredientList")

    object Playground : NavigationItem("playground", R.drawable.ic_playground, "Spielwiese")

}

