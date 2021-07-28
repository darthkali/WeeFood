package de.darthkali.weefood.datasource.database

import com.squareup.sqldelight.db.SqlDriver
import de.darthkali.weefood.datasource.network.IngredientServiceImpl
import de.darthkali.weefood.domain.model.Ingredient
import de.darthkali.weefood.util.Logger
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

class DatabaseHelper(
    sqlDriver: SqlDriver,
//    private val backgroundDispatcher: CoroutineDispatcher
) {
    private val dbRef: WeeFoodDatabase = WeeFoodDatabase(sqlDriver)
    private val logger = Logger("IngredientListViewModel")


   fun selectAllItems(): List<Ingredient> {
        return dbRef.ingredientDbQueries.getAllIngredients(
            pageSize = 100,
            offset = 0
        ).executeAsList().toIngredientList()
    }



    fun Ingredient_Entity.toIngredient(): Ingredient {
        return Ingredient(
            id = id.toInt(),
            name = name,
            image = image,
        )
    }


    fun List<Ingredient_Entity>.toIngredientList(): List<Ingredient>{
        return map{it.toIngredient()}
    }







    suspend fun insertIngredients(ingredients: List<Ingredient>) {
        logger.log("Inserting ${ingredients.size} ingredients into database")
        ingredients.forEach { ingredient ->
            dbRef.ingredientDbQueries.insertIngredient(null, ingredient.name ?: "", "no.jpg")
        }
    }

//    suspend fun selectById(id: Long): Flow<List<Breed>> =
//        dbRef.tableQueries
//            .selectById(id)
//            .asFlow()
//            .mapToList()
//            .flowOn(backgroundDispatcher)

    suspend fun deleteAll() {
        logger.log("Database Cleared")
            dbRef.ingredientDbQueries.deleteAllIngredients()
    }

//    suspend fun updateFavorite(breedId: Long, favorite: Boolean) {
//        log.i { "Breed $breedId: Favorited $favorite" }
//        dbRef.transactionWithContext(backgroundDispatcher) {
//            dbRef.tableQueries.updateFavorite(favorite.toLong(), breedId)
//        }
//    }
}

//fun Breed.isFavorited(): Boolean = this.favorite != 0L
//internal fun Boolean.toLong(): Long = if (this) 1L else 0L
