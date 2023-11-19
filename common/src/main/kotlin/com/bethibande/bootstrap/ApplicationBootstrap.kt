package com.bethibande.bootstrap

import com.bethibande.bootstrap.context.ApplicationContext
import com.bethibande.bootstrap.misc.logger
import com.bethibande.bootstrap.module.Module

class ApplicationBootstrap {

    companion object {
        private val LOGGER = logger()
    }

    private val modules = mutableListOf<Module>()
    private val context = ApplicationContext(modules)

    private val actions = mutableListOf<Runnable>()

    private fun withAction(action: Runnable): ApplicationBootstrap {
        actions.add(action)
        return this
    }

    fun withModule(module: Module) = withAction {
        LOGGER.info("Loading module '{}'", module.name())
        LOGGER.debug("Module of class '{}'", module::class.java)

        module.load(context)
        modules.add(module)
    }

    fun create() {
        actions.forEach { action ->
            action.run()
        }
    }

}

fun basicBootstrap(applicationModule: ApplicationContext.() -> Unit) = ApplicationBootstrap()
    .withModule(ApplicationModule(applicationModule))