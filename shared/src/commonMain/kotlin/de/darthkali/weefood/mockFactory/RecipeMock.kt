package de.darthkali.weefood.mockFactory

import de.darthkali.weefood.datasource.database.model.RecipeDb

object RecipeMock {


    val shortDescription = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam " +
            "nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam " +
            "voluptua. At vero eos et accusam."

    val longDescription = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam " +
            "nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam " +
            "voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita " +
            "kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem " +
            "ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor " +
            "invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et " +
            "accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata " +
            "sanctus est Lorem ipsum dolor sit amet."


    val recipeDb = RecipeDb(
        name = "Spinatauflauf",
        image = "spinatauflauf.jpg",
        cooking_time = 20,
        cooking_time_unit = "min",
        description = longDescription
    )

    val recipeListDb = listOf(
        RecipeDb(
            name = "Kartoffelbrei mit Sauerkraut und Bratwurs",
            image = "kartoffelbrei_sauerkraut_bratwurst.jpg",
            cooking_time = 120,
            cooking_time_unit = "min",
            description = longDescription
        ),
        RecipeDb(
            name = "Tomentensuppe",
            image = "tomentensuppe.jpg",
            cooking_time = 90,
            cooking_time_unit = "min",
            description = longDescription
        ),
        RecipeDb(
            name = "Tomaten Hirse Salat",
            image = "tomaten_hirse_salat.jpg",
            cooking_time = 20,
            cooking_time_unit = "min",
            description = longDescription
        ),
        RecipeDb(
            name = "Gebackener Schafskäse",
            image = "gebackener_schafskaese.jpg",
            cooking_time = 20,
            cooking_time_unit = "min",
            description = longDescription
        )
    )

    const val searchName = "Kartoffelbrei"
    const val searchResponseCount = 3

    val recipeListForSearchByName = listOf(
        RecipeDb(
            name = "$searchName mit Sauerkraut und Bratwurs",
            image = "true",
            cooking_time = 120,
            cooking_time_unit = "min",
            description = longDescription
        ),
        RecipeDb(
            name = "Tomentensuppe $searchName",
            image = "true",
            cooking_time = 90,
            cooking_time_unit = "min",
            description = longDescription
        ),
        RecipeDb(
            name = "Tomaten Hirse Salat",
            image = "false",
            cooking_time = 20,
            cooking_time_unit = "min",
            description = longDescription
        ),
        RecipeDb(
            name = "Gebackener  $searchName Schafskäse",
            image = "true",
            cooking_time = 20,
            cooking_time_unit = "min",
            description = longDescription
        )
    )
}