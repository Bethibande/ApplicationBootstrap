package com.bethibande.config.keys

import com.bethibande.bootstrap.misc.Reflection
import com.bethibande.config.Config
import kotlin.reflect.KClass

class EnumKey<T: Enum<T>>(
    key: String,
    config: Config,
    type: Class<T>,
): WritableConfigKey<T>(key, config, { Reflection.enumFromString(type, it) }, { it!!.name }, null, false) {

    constructor(key: String, config: Config, type: KClass<T>): this(key, config, type.java)

}