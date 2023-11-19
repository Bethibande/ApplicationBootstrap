package com.bethibande.config

import com.bethibande.config.keys.BooleanKey
import com.bethibande.config.keys.ConfigKey
import com.bethibande.config.keys.EnumKey
import com.bethibande.config.keys.IntKey
import com.bethibande.config.keys.StringKey
import kotlin.reflect.KClass

interface Config {

    fun get(key: String): String?

    fun string(key: String): ConfigKey<String> = StringKey(key, this)
    fun int(key: String): ConfigKey<Int> = IntKey(key, this)
    fun bool(key: String): ConfigKey<Boolean> = BooleanKey(key, this)
    fun <T: Enum<T>> enum(key: String, type: KClass<T>): ConfigKey<T> = EnumKey(key, this, type)

}