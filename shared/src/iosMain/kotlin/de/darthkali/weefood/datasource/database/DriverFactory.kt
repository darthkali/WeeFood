package de.darthkali.weefood.datasource.database

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import org.koin.core.component.KoinComponent

actual class DriverFactory: KoinComponent {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(
            schema = WeeFoodDatabase.Schema,
            name = "weeFoodDatabase.db")
    }
}