package br.com.lucasladeira.mercadolivro.repositories

import br.com.lucasladeira.mercadolivro.entities.Book
import br.com.lucasladeira.mercadolivro.enums.BookStatus
import org.springframework.data.jpa.repository.JpaRepository

interface BookRepository: JpaRepository<Book, Long> {
    fun findByStatus(status: BookStatus): List<Book>
}