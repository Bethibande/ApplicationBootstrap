package com.bethibande.bootstrap

import io.ktor.server.routing.Route

interface WebHandler {

    fun install(route: Route) {
        route.installRoutes()
    }

    fun Route.installRoutes(): Route

}

fun Route.installHandlers(vararg handlers: WebHandler) {
    handlers.forEach { handler -> handler.install(this) }
}