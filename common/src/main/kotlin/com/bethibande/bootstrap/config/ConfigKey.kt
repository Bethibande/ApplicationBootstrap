package com.bethibande.bootstrap.config

import kotlin.reflect.KProperty

class ConfigKey<T>(
    private val key: String,
    private val parser: (String) -> T,
    private val config: Config,
    private val required: Boolean = true
) {

    private var default: T? = null

    fun key() = this.key
    fun default() = this.default
    fun isRequired() = this.required
    fun default(value: T): ConfigKey<T> {
        this.default = value
        return this
    }
    fun optional(): ConfigKey<T?> {
        return ConfigKey(key, parser, config, false)
    }

    fun parser() = this.parser

    fun get() = config.get(this)

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return get()
    }

}