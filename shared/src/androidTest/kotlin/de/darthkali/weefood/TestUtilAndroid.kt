package de.darthkali.weefood


import android.app.Application
import androidx.test.core.app.ApplicationProvider
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import de.darthkali.weefood.datasource.database.WeeFoodDatabase

internal actual fun testDbConnection(): SqlDriver {
    val app = ApplicationProvider.getApplicationContext<Application>()
    return AndroidSqliteDriver(WeeFoodDatabase.Schema, app, "weeFoodDatabase")
}
