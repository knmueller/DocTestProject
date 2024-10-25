package com.doctest

import io.github.tabilzad.ktor.annotations.GenerateOpenApi
import io.github.tabilzad.ktor.annotations.KtorDescription
import io.github.tabilzad.ktor.annotations.KtorResponds
import io.github.tabilzad.ktor.annotations.ResponseEntry
import io.github.tabilzad.ktor.annotations.Tag
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.swagger.swaggerUI
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun main() {
    embeddedServer(Netty, port = SERVER_PORT, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

@GenerateOpenApi
fun Application.module() {
    routing {
        @Tag(["docs"])
        @KtorResponds(
            mapping = [
                ResponseEntry("200", String::class),
            ]
        )
        @KtorDescription(
            summary = "Get test",
            description = "Returns a string"
        )
        get("/") {
            call.respondText("Ktor: ${Greeting().greet()}")
        }

        swaggerUI("swagger","openapi/openapi.yaml"){
            version = "5.17.12"
        }
    }
}