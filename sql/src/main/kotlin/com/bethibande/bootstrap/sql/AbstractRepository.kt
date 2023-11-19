package com.bethibande.bootstrap.sql

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.Transaction
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

abstract class AbstractRepository(private val database: Database) {

    protected fun createTables(vararg tables: Table) {
        transaction(database) { SchemaUtils.create(*tables) }
    }

    protected suspend fun <R> transact(fn: suspend Transaction.() -> R): R {
        return newSuspendedTransaction(db = database) {
            fn.invoke(this)
        }
    }

}