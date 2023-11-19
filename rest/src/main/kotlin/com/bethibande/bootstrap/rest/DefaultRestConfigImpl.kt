package com.bethibande.bootstrap.rest

import com.bethibande.bootstrap.config.Config

class DefaultRestConfigImpl(private val config: Config): RestConfig {

    private val host by config.string("rest.host")
    private val port by config.int("rest.port")

    override fun host(): String = host

    override fun port(): Int = port
}