package com.bethibande.bootstrap.sql

import org.jetbrains.exposed.sql.Database

class SqlConnectionFactory(private val config: SqlConfig) {

    companion object {
        const val PLACEHOLDER_HOST = "%host%"
        const val PLACEHOLDER_DATABASE = "%database%"
    }

    private val connection by lazy { connect() }

    private fun buildUrl(): String {
        var url = config.driver().urlSchema()
        url = url.replace(PLACEHOLDER_HOST, config.host())
        url = url.replace(PLACEHOLDER_DATABASE, config.database())

        return url
    }

    private fun connect(): Database {
        val url = buildUrl()
        return Database.connect(url, config.driver().driver(), config.username(), config.password())
    }

    fun connection() = connection

}