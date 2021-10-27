package de.darthkali.weefood.di

import com.squareup.sqldelight.android.AndroidSqliteDriver
import de.darthkali.weefood.datasource.database.WeeFoodDatabase
import de.darthkali.weefood.datasource.database.WeeFoodDatabaseWrapper
import org.koin.dsl.module

// TODO JavaDoc einfügen
actual fun platformModule() = module {
    single {
        val driver =
            AndroidSqliteDriver(WeeFoodDatabase.Schema, get(), "weeFoodDatabase.db")
        WeeFoodDatabaseWrapper(WeeFoodDatabase(driver))
    }
}
