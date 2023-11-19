package com.bethibande.config.keys

import com.bethibande.config.Config

class IntKey(
    key: String,
    config: Config
): WritableConfigKey<Int>(key, config, { it.toInt() }, { it!!.toString() }, null, false)