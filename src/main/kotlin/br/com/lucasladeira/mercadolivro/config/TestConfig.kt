package br.com.lucasladeira.mercadolivro.config

import br.com.lucasladeira.mercadolivro.entities.Book
import br.com.lucasladeira.mercadolivro.entities.Customer
import br.com.lucasladeira.mercadolivro.enums.BookStatus
import br.com.lucasladeira.mercadolivro.enums.CustomerStatus
import br.com.lucasladeira.mercadolivro.enums.Role
import br.com.lucasladeira.mercadolivro.repositories.BookRepository
import br.com.lucasladeira.mercadolivro.repositories.CustomerRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.math.BigDecimal
import java.util.*

@Configuration
@Profile("dev")
class TestConfig(
    var bookRepository: BookRepository,
    var customerRepository: CustomerRepository,
    var bCryptPasswordEncoder: BCryptPasswordEncoder
) : CommandLineRunner{
    override fun run(vararg args: String?) {

        val c1 = Customer(
            null,
            "Lucas Ladeira",
            "ladeira222@hotmail.com",
            CustomerStatus.ATIVO,
            "senha4321"
        )

        val c2 = Customer(
            null,
            "Rafael Lima",
            "rafa2@hotmail.com",
            CustomerStatus.ATIVO,
            bCryptPasswordEncoder.encode("senha123")
        )

        val c3 = Customer(
            null,
            "Henrique Fernandes",
            "henri51@hotmail.com",
            CustomerStatus.ATIVO,
            bCryptPasswordEncoder.encode("lalalalv3213")
        )

        val c4 = Customer(
            null,
            "ADMINISTRADOR",
            "adm@hotmail.com",
            CustomerStatus.ATIVO,
            bCryptPasswordEncoder.encode("supersecreto"),
            setOf(Role.ADMIN, Role.CUSTOMER)
        )
        customerRepository.saveAll(listOf(c1, c2, c3, c4))


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

        val b3 = Book(
            null,
            "Clean Code",
            BigDecimal(99),
            null,
            BookStatus.DELETADO
        )
        bookRepository.saveAll(Arrays.asList(b1, b2, b3))
    }
}