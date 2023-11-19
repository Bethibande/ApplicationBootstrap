package com.bethibande.bootstrap

import com.bethibande.bootstrap.context.ApplicationContext
import com.bethibande.bootstrap.module.Module

class ApplicationModule(private val module: ApplicationContext.() -> Unit): Module() {

    override fun ApplicationContext.initialize() {
        this.module()
    }
}