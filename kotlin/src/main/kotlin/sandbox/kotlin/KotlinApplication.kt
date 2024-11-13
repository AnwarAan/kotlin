package sandbox.kotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinApplication

fun main(args: Array<String>) {
	println("Ampun Bang")
	runApplication<KotlinApplication>(*args)
}
