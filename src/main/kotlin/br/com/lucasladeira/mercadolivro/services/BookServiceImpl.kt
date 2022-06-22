package br.com.lucasladeira.mercadolivro.services

import br.com.lucasladeira.mercadolivro.dto.BookDTO
import br.com.lucasladeira.mercadolivro.dto.NewBookDTO
import br.com.lucasladeira.mercadolivro.entities.Book
import br.com.lucasladeira.mercadolivro.entities.Customer
import br.com.lucasladeira.mercadolivro.repositories.BookRepository
import br.com.lucasladeira.mercadolivro.utils.DTOUtils
import org.springframework.stereotype.Service

@Service
class BookServiceImpl(
    var bookRepository: BookRepository,
    var customerService: CustomerService,
    var dtoUtils: DTOUtils,
): BookService {


    override fun save(bookDTO: NewBookDTO): BookDTO {
        val customer: Customer = customerService.getById(bookDTO.customerId)
        var book: Book = dtoUtils.fromDTO(bookDTO, Book::class.java)

        book.customer = customer

        book = bookRepository.save(book)

        return dtoUtils.toDTO(book, BookDTO::class.java)
    }
}