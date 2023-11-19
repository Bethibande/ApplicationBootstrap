package com.bethibande.bootstrap.rest

import com.bethibande.bootstrap.GLOBAL_JACKSON_MAPPER
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import io.ktor.serialization.jackson.JacksonWebsocketContentConverter
import io.ktor.serialization.jackson.jackson
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.websocket.WebSockets

abstract class RestServer(private val config: RestConfig) {

    fun start() {
        embeddedServer(Netty, host = config.host(), port = config.port()) {
            installPlugins()
            installRouting()
        }.start(true)
    }

    abstract fun Application.installCustomPlugins()

    private fun Application.installPlugins() {
        install(ContentNegotiation) {
            jackson {
                registerKotlinModule()
                registerModule(JavaTimeModule())
                configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
            }
        }
        install(WebSockets) {
            contentConverter = JacksonWebsocketContentConverter(GLOBAL_JACKSON_MAPPER)
        }
        installCustomPlugins()
    }

    private fun Application.installRouting() {

    }

}