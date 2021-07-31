package de.darthkali.weefood.datasource.database

import de.darthkali.weefood.domain.model.Ingredient

interface RecipeCache {

    fun insert(ingredient: Ingredient)
    fun insert(ingredients: List<Ingredient>)
    fun search(query: String, page: Int): List<Ingredient>
    fun getAll(page: Int): List<Ingredient>

    @Throws(NullPointerException::class)
    fun get(recipeId: Int): Ingredient?
}