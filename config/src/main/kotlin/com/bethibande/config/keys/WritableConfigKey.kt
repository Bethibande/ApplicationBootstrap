package com.bethibande.config.keys

import com.bethibande.config.Config
import com.bethibande.config.WritableConfig
import kotlin.reflect.KProperty

open class WritableConfigKey<T>(
    private val key: String,
    private val config: Config,
    private val deserializer: (String) -> T,
    private val serializer: (T?) -> String,
    private val default: T?,
    private val optional: Boolean
): ConfigKey<T> {

    override fun key(): String = key

    override fun config(): Config = config

    override fun default(value: T): WritableConfigKey<T> = WritableConfigKey(
        key,
        config,
        deserializer,
        serializer,
        value,
        optional
    )

    override fun optional(): WritableConfigKey<T?> = WritableConfigKey(
        key,
        config,
        deserializer,
        serializer,
        default,
        true
    )

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        if (config !is WritableConfig) {
            throw IllegalStateException("The config this key maps to is not writable")
        }
        if (value == null) {
            config.set(key, null)
        } else {
            config.set(key, serializer.invoke(value))
        }
    }

    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        val raw = config.get(key)
        if (raw == null) {
            if (default != null) return default
            if (optional) return null as T
            throw IllegalStateException("Missing value for config key '$key'")
        }

        return deserializer.invoke(raw)    }
}