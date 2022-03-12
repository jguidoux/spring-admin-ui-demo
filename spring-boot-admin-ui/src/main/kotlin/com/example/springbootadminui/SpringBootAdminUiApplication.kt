package com.example.springbootadminui

import de.codecentric.boot.admin.server.config.EnableAdminServer
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Configuration

@SpringBootApplication
@EnableAdminServer
open class SpringBootAdminUiApplication

fun main(args: Array<String>) {
	runApplication<SpringBootAdminUiApplication>(*args)
}

