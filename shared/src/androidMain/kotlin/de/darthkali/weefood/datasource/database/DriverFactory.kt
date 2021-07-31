package de.darthkali.weefood.datasource.database

import android.content.Context
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

actual class DriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(
            schema = WeeFoodDatabase.Schema,
            context = context,
            name = "weeFoodDatabase.db")
    }
}