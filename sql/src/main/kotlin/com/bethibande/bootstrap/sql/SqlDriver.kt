package com.bethibande.bootstrap.sql

import java.sql.Driver
import kotlin.reflect.KClass

enum class SqlDriver(private val clazz: KClass<out Driver>, private val urlSchema: String) {

    MYSQL(com.mysql.jdbc.Driver::class, "jdbc:mysql://%host%/%database%"),
    POSTGRESQL(org.postgresql.Driver::class, "jdbc:postgresql://%host%/%database%"),
    MARIA_DB(org.mariadb.jdbc.Driver::class, "jdbc:mariadb://%host%/%database%");

    fun driver(): String = clazz.java.name
    fun driverClass(): KClass<out Driver> = clazz

    fun urlSchema(): String = urlSchema

}