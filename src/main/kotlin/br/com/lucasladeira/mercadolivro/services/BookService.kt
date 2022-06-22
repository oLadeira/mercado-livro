package br.com.lucasladeira.mercadolivro.services

import br.com.lucasladeira.mercadolivro.dto.BookDTO
import br.com.lucasladeira.mercadolivro.dto.NewBookDTO

interface BookService {

    fun save(bookDTO: NewBookDTO): BookDTO
}