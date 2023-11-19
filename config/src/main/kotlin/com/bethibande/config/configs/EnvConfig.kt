package com.bethibande.config.configs

import com.bethibande.config.Config

class EnvConfig: Config {

    override fun get(key: String): String? = System.getenv(key)
}