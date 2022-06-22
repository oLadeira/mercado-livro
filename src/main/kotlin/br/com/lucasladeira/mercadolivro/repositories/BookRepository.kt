package br.com.lucasladeira.mercadolivro.repositories

import br.com.lucasladeira.mercadolivro.entities.Book
import org.springframework.data.jpa.repository.JpaRepository

interface BookRepository: JpaRepository<Book, Long> {
}