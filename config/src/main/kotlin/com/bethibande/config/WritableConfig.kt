package com.bethibande.config

import com.bethibande.config.keys.BooleanKey
import com.bethibande.config.keys.EnumKey
import com.bethibande.config.keys.IntKey
import com.bethibande.config.keys.StringKey
import com.bethibande.config.keys.WritableConfigKey
import kotlin.reflect.KClass

interface WritableConfig: Config {

    override fun string(key: String): WritableConfigKey<String> = StringKey(key, this)
    override fun int(key: String): WritableConfigKey<Int> = IntKey(key, this)
    override fun bool(key: String): WritableConfigKey<Boolean> = BooleanKey(key, this)
    override fun <T: Enum<T>> enum(key: String, type: KClass<T>): WritableConfigKey<T> = EnumKey(key, this, type)

    fun set(key: String, value: String?)

}