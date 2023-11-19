package com.bethibande.bootstrap.config

import java.nio.file.Path
import java.util.Properties
import kotlin.io.path.Path
import kotlin.io.path.inputStream

interface IConfigProvider {

    fun getValue(key: String): String?

}

class EnvConfigProvider: IConfigProvider {

    override fun getValue(key: String): String? = System.getenv(key)

}

class PropertiesConfigProvider(private val properties: Properties): IConfigProvider {

    override fun getValue(key: String): String? = properties.getProperty(key)

}

/**
 * Accepts files in a simple env like format,
 * ```text
 * KEY1=some value
 * KEY2=some other value
 *
 * # comment
 * KEY3=yet another value
 * ```
 */
class FileConfigProvider(private val file: Path): IConfigProvider {

    companion object {
        @JvmStatic
        fun of(file: String) = FileConfigProvider(Path(file))

    }

    private val properties by lazy { load() }
    private val config by lazy { PropertiesConfigProvider(properties) }

    private fun load(): Properties {
        val properties = Properties()
        file.inputStream().use { inp -> properties.load(inp) }
        return properties
    }

    override fun getValue(key: String): String? = config.getValue(key)
}

class PropertyConfigProvider: IConfigProvider {

    override fun getValue(key: String): String? = System.getProperty(key)

}