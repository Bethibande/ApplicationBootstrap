package com.bethibande.config.keys

import com.bethibande.config.Config

class StringKey(
    key: String,
    config: Config
): WritableConfigKey<String>(key, config, { it }, { it!! }, null, false)