package br.com.lucasladeira.mercadolivro.config

import br.com.lucasladeira.mercadolivro.entities.Book
import br.com.lucasladeira.mercadolivro.entities.Customer
import br.com.lucasladeira.mercadolivro.enums.CustomerStatus
import br.com.lucasladeira.mercadolivro.repositories.BookRepository
import br.com.lucasladeira.mercadolivro.repositories.CustomerRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import java.math.BigDecimal
import java.util.*

@Configuration
@Profile("dev")
class TestConfig(
    var bookRepository: BookRepository,
    var customerRepository: CustomerRepository
) : CommandLineRunner{
    override fun run(vararg args: String?) {

        val c1 = Customer(
            null,
            "Lucas Ladeira",
            "ladeira222@hotmail.com",
            CustomerStatus.ATIVO
        )

        val c2 = Customer(
            null,
            "Rafael Lima",
            "rafa2@hotmail.com",
            CustomerStatus.ATIVO
        )

        val c3 = Customer(
            null,
            "Henrique Fernandes",
            "henri51@hotmail.com",
            CustomerStatus.ATIVO
        )
        customerRepository.saveAll(Arrays.asList(c1, c2, c3))


        val b1 = Book(
            null,
            "Iracema",
            BigDecimal(44),
            c1
        )
        val b2 = Book(
            null,
            "Clean Code",
            BigDecimal(99),
            c2
        )
        bookRepository.saveAll(Arrays.asList(b1, b2))
    }
}