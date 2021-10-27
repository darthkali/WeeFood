package de.darthkali.weefood.datasource.database.mapper.recipe

import de.darthkali.weefood.datasource.BaseMapper
import de.darthkali.weefood.datasource.database.model.RecipeDb
import de.darthkali.weefood.domain.model.Recipe

class RecipeListMapper : BaseMapper<List<Recipe>, List<RecipeDb>> {

    private val internalMapper = RecipeMapper()

    override fun mapTo(db: List<RecipeDb>): List<Recipe> {
        val resultList = mutableListOf<Recipe>()

        db.forEach {
            resultList.add(internalMapper.mapTo(it))
        }
        return resultList
    }

    override fun mapBack(internal: List<Recipe>): List<RecipeDb> {
        val resultList = mutableListOf<RecipeDb>()

        internal.forEach {
            resultList.add(internalMapper.mapBack(it))
        }
        return resultList
    }
}
