package com.bethibande.config.configs

import com.bethibande.config.WritableConfig

class PropertyConfig: WritableConfig {

    override fun get(key: String): String? = System.getProperty(key)

    override fun set(key: String, value: String?) {
        value?.let{ System.setProperty(key, value) }
    }
}