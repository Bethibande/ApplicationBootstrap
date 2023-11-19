package com.bethibande.config.keys

import com.bethibande.config.Config

class BooleanKey(
    key: String,
    config: Config
): WritableConfigKey<Boolean>(key, config, { it.toBoolean() }, { it!!.toString() }, null, false)