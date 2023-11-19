package com.bethibande.config.configs

import com.bethibande.config.WritableConfig

interface FileConfig: WritableConfig {

    fun flush()

}