package de.darthkali.weefood.di

import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import de.darthkali.weefood.datasource.database.WeeFoodDatabase
import de.darthkali.weefood.datasource.database.WeeFoodDatabaseWrapper
import org.koin.dsl.module

actual fun platformModule() = module {
    single {
        val driver = NativeSqliteDriver(WeeFoodDatabase.Schema, "weeFoodDatabase.db")
        WeeFoodDatabaseWrapper(WeeFoodDatabase(driver))
    }
}
