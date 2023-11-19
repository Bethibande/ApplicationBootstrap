package com.bethibande.bootstrap.misc

import java.lang.reflect.Executable
import java.lang.reflect.Modifier

object Reflection {

    fun instanceof(obj: Any, type: Class<*>): Boolean = type.isAssignableFrom(obj::class.java)

    fun isPublic(executable: Executable): Boolean = Modifier.isPublic(executable.modifiers)

    fun areParameters(executable: Executable, vararg types: Class<*>): Boolean {
        val parameters = executable.parameters
        if (types.size != parameters.size) return false

        return parameters.filterIndexed { idx, parameter -> !parameter.type.isAssignableFrom(types[idx]) }.isEmpty()
    }

    fun <T: Enum<T>> enumFromString(type: Class<T>, string: String): T = type.enumConstants.first { it.name == string }

}