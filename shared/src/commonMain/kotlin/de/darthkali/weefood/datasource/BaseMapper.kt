package de.darthkali.weefood.datasource

interface BaseMapper<I, D> {
    fun mapTo(db: D): I
    fun mapBack(internal: I): D
}
