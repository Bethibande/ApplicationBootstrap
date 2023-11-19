package com.bethibande.config.configs

import java.nio.file.Path
import java.nio.file.StandardOpenOption
import java.util.Properties
import kotlin.io.path.createFile
import kotlin.io.path.exists
import kotlin.io.path.inputStream
import kotlin.io.path.outputStream

class PropertiesFileConfig(private val file: Path): FileConfig {

    private val properties by lazy { initialLoad() }

    private fun initialLoad(): Properties {
        val props = Properties()

        if (!file.exists()) file.createFile()
        file.inputStream().use { input ->
            props.load(input)
        }

        return props
    }

    override fun flush() {
        file.outputStream(StandardOpenOption.CREATE).use { output ->
            properties.store(output, null)
        }
    }

    override fun get(key: String): String? = properties.getProperty(key)

    override fun set(key: String, value: String?) {
        properties[key] = value
        flush()
    }
}