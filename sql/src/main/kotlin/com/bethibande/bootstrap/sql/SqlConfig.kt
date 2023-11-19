package com.bethibande.bootstrap.sql

import com.bethibande.bootstrap.config.Config

interface SqlConfig {

    fun host(): String
    fun driver(): SqlDriver

    fun username(): String
    fun password(): String

    fun database(): String

}

class DefaultSqlConfigImpl(val config: Config): SqlConfig {

    companion object {
        const val KEY_HOST     = "sql.host"
        const val KEY_DRIVER   = "sql.driver"
        const val KEY_USER     = "sql.user"
        const val KEY_PASSWD   = "sql.password"
        const val KEY_DATABASE = "sql.database"
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