package com.bethibande.bootstrap.config

import kotlin.io.path.Path

val DEFAULT_CONFIG_PROVIDERS = listOf(
    EnvConfigProvider(),
    PropertyConfigProvider(),
    FileConfigProvider(Path("global.properties"))
)

class Config(private val providers: List<IConfigProvider>) {

    fun string(name: String): ConfigKey<String> = ConfigKey(name, { it }, this)
    fun int(name: String): ConfigKey<Int> = ConfigKey(name, { it.toInt() }, this)
    fun bool(name: String): ConfigKey<Boolean> = ConfigKey(name, { it.toBoolean() }, this)
    inline fun <reified T: Enum<T>> enum(name: String): ConfigKey<T> = ConfigKey(name, { enumValueOf<T>(it) }, this)
    inline fun <reified T> classFile(name: String): ConfigKey<Class<out T>> = ConfigKey(name, {
        val clazz = Class.forName(it)
        if (!T::class.java.isAssignableFrom(clazz)) throw IllegalArgumentException("Class ${T::class.java} is not assignable from $name")
        return@ConfigKey clazz as Class<out T>
    }, this)

    private fun getRaw(key: String): String? = providers.firstNotNullOfOrNull { it.getValue(key) }

    @Suppress("UNCHECKED_CAST")
    fun <T> get(key: ConfigKey<T>): T {
        val raw = getRaw(key.key())
        if (raw == null && key.default() != null) return key.default()!!
        if (raw == null && key.isRequired()) throw IllegalStateException("No value found for key '${key.key()}'")
        if (raw == null) return null as T
        return key.parser().invoke(raw)
    }

}