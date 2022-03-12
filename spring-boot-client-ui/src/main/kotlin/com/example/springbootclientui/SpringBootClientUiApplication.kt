package com.example.springbootclientui

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
open class SpringBootClientUiApplication

fun main(args: Array<String>) {
	runApplication<SpringBootClientUiApplication>(*args)
}

@Configuration
open class SecurityPermitAllConfig : WebSecurityConfigurerAdapter() {
	@Throws(Exception::class)
	override fun configure(http: HttpSecurity) {
		http.authorizeRequests().anyRequest().permitAll()
			.and().csrf().disable()
	}
}

data class Message(val id: String?, val text: String)


@RestController
@RequestMapping("/message")
class MessageResource {
	@GetMapping
	fun index(): List<Message> = listOf(
		Message("1", "Hello!"),
		Message("2", "Bonjour!"),
		Message("3", "Privet!"),
	)
}