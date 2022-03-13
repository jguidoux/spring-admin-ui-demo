package com.example.people

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*
import kotlin.collections.HashMap

@SpringBootApplication
class SpringBootClientUiApplication {
	@Bean
	fun init(repository: PeopleRepository) = CommandLineRunner {
		listOf("jerem", "karin", "Batman" )
			.map { People(name = it) }
			.forEach{ repository.save(it) }
	}
}

fun main(args: Array<String>) {
	runApplication<SpringBootClientUiApplication>(*args)
	}


@Configuration
class SecurityPermitAllConfig : WebSecurityConfigurerAdapter() {
	@Throws(Exception::class)
	override fun configure(http: HttpSecurity) {
		http.authorizeRequests().anyRequest().permitAll()
			.and().csrf().disable()
	}
}

data class People(val id: String = UUID.randomUUID().toString(), val name: String)


@RestController
@RequestMapping("/people")
class MessageResource(private val repository: PeopleRepository) {
	@GetMapping
	fun allPeople() =  repository.findAll()
}

@Component
class PeopleRepository(val peoples: HashMap<String, People> = hashMapOf()) {
	fun findAll() = peoples.values
	fun save(newGuy: People) = peoples.put(newGuy.id, newGuy)

}