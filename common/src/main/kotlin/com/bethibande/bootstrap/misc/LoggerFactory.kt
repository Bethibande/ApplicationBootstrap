package com.bethibande.bootstrap.misc

import org.slf4j.Logger
import org.slf4j.LoggerFactory

fun logger(): Logger = logger(1)

fun logger(offset: Int): Logger {
    val stack = Thread.currentThread().stackTrace
    if (stack.size < 3 + offset) throw IllegalStateException("How did we get here? This shouldn't be possible.")
    try {
        val caller = Class.forName(stack[2 + offset].className)
        return LoggerFactory.getLogger(caller)
    } catch (notFound: ClassNotFoundException) {
        throw IllegalStateException("The current scope is not able to call this method.")
    }
}