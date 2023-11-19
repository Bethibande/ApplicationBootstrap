package com.bethibande.bootstrap.misc

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset

object DateHelper {

    private val ZONE = ZoneOffset.UTC

    @JvmStatic
    fun now() = LocalDateTime.now(ZONE)

    @JvmStatic
    fun parse(str: String) = LocalDateTime.parse(str)

    @JvmStatic
    fun Instant.toDateTime(): LocalDateTime = LocalDateTime.ofInstant(this, ZONE)

}