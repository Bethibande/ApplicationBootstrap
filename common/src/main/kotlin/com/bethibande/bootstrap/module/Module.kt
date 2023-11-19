package com.bethibande.bootstrap.module

import com.bethibande.bootstrap.context.ApplicationContext
import com.bethibande.bootstrap.misc.logger

abstract class Module {

    private var initialized = false
    private val logger = logger(1)

    fun name() = this::class.java.simpleName
    fun initialized() = initialized

    protected fun logger() = logger

    fun load(ctx: ApplicationContext) {
        if (initialized) throw IllegalStateException("Module '${name()}' has already been initialized")

        ctx.initialize()
        initialized = true
    }

    protected abstract fun ApplicationContext.initialize()

}