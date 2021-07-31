package de.darthkali.weefood

import com.squareup.sqldelight.db.SqlDriver

internal expect fun testDbConnection(): SqlDriver

fun writeHead(name: String){
    println("-------------------------------------------")
    println("---$name")
    println("-------------------------------------------")
}