package com.bethibande.bootstrap.sql

import com.bethibande.bootstrap.config.Config

interface SqlConfig {

    fun host(): String
    fun driver(): SqlDriver

    fun username(): String
    fun password(): String

    fun database(): String

}

class StandardSqlConfigImpl(val config: Config): SqlConfig {

    companion object {
        const val KEY_HOST     = "SQL_HOST"
        const val KEY_DRIVER   = "SQL_DRIVER"
        const val KEY_USER     = "SQL_USER"
        const val KEY_PASSWD   = "SQL_PASSWORD"
        const val KEY_DATABASE = "SQL_DATABASE"
    }

    private val host by config.string(KEY_HOST)
    private val driver by config.enum<SqlDriver>(KEY_DRIVER)
    private val user by config.string(KEY_USER)
    private val password by config.string(KEY_PASSWD)
    private val database by config.string(KEY_DATABASE)

    override fun host(): String = host

    override fun driver(): SqlDriver = driver

    override fun username(): String = user

    override fun password(): String = password

    override fun database(): String = database
}