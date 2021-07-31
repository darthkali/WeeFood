package de.darthkali.weefood.datasource.database

import android.content.Context
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import org.koin.core.component.KoinComponent

actual class DriverFactory(private val context: Context):KoinComponent {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(
            schema = WeeFoodDatabase.Schema,
            context = context,
            name = "weeFoodDatabase.db")
    }
}