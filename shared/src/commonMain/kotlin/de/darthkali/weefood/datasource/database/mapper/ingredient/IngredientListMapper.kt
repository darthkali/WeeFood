package de.darthkali.weefood.datasource.database.mapper.ingredient

import de.darthkali.weefood.datasource.BaseMapper
import de.darthkali.weefood.datasource.database.model.IngredientDb
import de.darthkali.weefood.domain.model.Ingredient

class IngredientListMapper: BaseMapper<List<Ingredient>, List<IngredientDb>> {

    private val internalMapper = IngredientMapper()

    override fun mapTo(db: List<IngredientDb>): List<Ingredient> {
        val resultList = mutableListOf<Ingredient>()

        db.forEach {
            resultList.add(internalMapper.mapTo(it))
        }
        return resultList
    }

    override fun mapBack(internal: List<Ingredient>): List<IngredientDb> {
        val resultList = mutableListOf<IngredientDb>()

        internal.forEach {
            resultList.add(internalMapper.mapBack(it))
        }
        return resultList
    }
}