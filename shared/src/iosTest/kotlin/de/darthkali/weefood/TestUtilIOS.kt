package de.darthkali.weefood

import co.touchlab.sqliter.DatabaseConfiguration
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import com.squareup.sqldelight.drivers.native.wrapConnection
import de.darthkali.weefood.datasource.database.WeeFoodDatabase

internal actual fun testDbConnection(): SqlDriver {
    val schema = WeeFoodDatabase.Schema
    return NativeSqliteDriver(
        DatabaseConfiguration(
            name = "weeFoodDatabase",
            version = schema.version,
            create = { connection ->
                wrapConnection(connection) { schema.create(it) }
            },
            upgrade = { connection, oldVersion, newVersion ->
                wrapConnection(connection) { schema.migrate(it, oldVersion, newVersion) }
            },
            inMemory = true
        )
    )
}

//TODO: DB TestConnection wirklich nötig? Warum nicht die DB Factory nutzen