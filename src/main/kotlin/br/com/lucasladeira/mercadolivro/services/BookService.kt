package br.com.lucasladeira.mercadolivro.services

import br.com.lucasladeira.mercadolivro.dto.BookDTO
import br.com.lucasladeira.mercadolivro.dto.NewBookDTO
import br.com.lucasladeira.mercadolivro.entities.Book

interface BookService {

    fun save(bookDTO: NewBookDTO): BookDTO
    fun getAll(): List<BookDTO>
    fun getById(id: Long): Book
}