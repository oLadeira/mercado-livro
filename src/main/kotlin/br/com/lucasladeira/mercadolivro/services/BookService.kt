package br.com.lucasladeira.mercadolivro.services

import br.com.lucasladeira.mercadolivro.dto.BookDTO
import br.com.lucasladeira.mercadolivro.dto.NewBookDTO
import br.com.lucasladeira.mercadolivro.dto.UpdateBookDTO
import br.com.lucasladeira.mercadolivro.entities.Book
import br.com.lucasladeira.mercadolivro.entities.Customer
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface BookService {

    fun save(bookDTO: NewBookDTO): BookDTO
    /*fun getAll(): List<BookDTO>*/
    fun getAll(pageagle: Pageable): Page<BookDTO>
    fun getById(id: Long): Book
    fun getActives(): List<BookDTO>
    fun delete(id: Long)
    fun update(id: Long, updatedBook: UpdateBookDTO): BookDTO
    fun deleteByCustomer(customer: Customer)
}