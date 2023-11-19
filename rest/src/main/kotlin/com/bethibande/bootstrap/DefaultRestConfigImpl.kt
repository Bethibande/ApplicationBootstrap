package com.bethibande.bootstrap

import com.bethibande.bootstrap.config.Config

class DefaultRestConfigImpl(private val config: Config): RestConfig {

    private val host by config.string("REST_HOST")
    private val port by config.int("REST_PORT")

    override fun host(): String = host

    override fun port(): Int = port
}