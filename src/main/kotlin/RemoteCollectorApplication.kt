package org.example

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RemoteCollectorApplication

fun main(args: Array<String>) {
    runApplication<RemoteCollectorApplication>(*args)
}