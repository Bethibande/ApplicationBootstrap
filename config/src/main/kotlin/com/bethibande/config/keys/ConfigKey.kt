package com.bethibande.config.keys

import com.bethibande.config.Config
import kotlin.reflect.KProperty

interface ConfigKey<T> {

    fun key(): String
    fun config(): Config

    fun default(value: T): ConfigKey<T>

    fun optional(): ConfigKey<T?>

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T

}