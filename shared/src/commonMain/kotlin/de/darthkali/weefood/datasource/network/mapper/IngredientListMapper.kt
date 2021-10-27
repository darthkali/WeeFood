package de.darthkali.weefood.datasource.network.mapper

import de.darthkali.weefood.datasource.BaseMapper
import de.darthkali.weefood.datasource.network.model.IngredientDto
import de.darthkali.weefood.domain.model.Ingredient

class IngredientListMapper : BaseMapper<List<Ingredient>, List<IngredientDto>> {

    private val internalMapper = IngredientMapper()

    override fun mapTo(db: List<IngredientDto>): List<Ingredient> {
        val resultList = mutableListOf<Ingredient>()

        db.forEach {
            resultList.add(internalMapper.mapTo(it))
        }
        return resultList
    }

    override fun mapBack(internal: List<Ingredient>): List<IngredientDto> {
        val resultList = mutableListOf<IngredientDto>()

        internal.forEach {
            resultList.add(internalMapper.mapBack(it))
        }
        return resultList
    }
}
