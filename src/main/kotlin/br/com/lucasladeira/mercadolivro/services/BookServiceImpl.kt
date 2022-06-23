package br.com.lucasladeira.mercadolivro.services

import br.com.lucasladeira.mercadolivro.dto.BookDTO
import br.com.lucasladeira.mercadolivro.dto.NewBookDTO
import br.com.lucasladeira.mercadolivro.dto.UpdateBookDTO
import br.com.lucasladeira.mercadolivro.entities.Book
import br.com.lucasladeira.mercadolivro.entities.Customer
import br.com.lucasladeira.mercadolivro.enums.BookStatus
import br.com.lucasladeira.mercadolivro.repositories.BookRepository
import br.com.lucasladeira.mercadolivro.utils.DTOUtils
import org.springframework.beans.BeanUtils
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.Optional
import javax.persistence.EntityNotFoundException

@Service
class BookServiceImpl(
    var bookRepository: BookRepository,
    var customerService: CustomerService,
    var dtoUtils: DTOUtils,
): BookService {


    override fun save(bookDTO: NewBookDTO): BookDTO {
        val customer: Customer = customerService.getById(bookDTO.customerId)
        var book: Book = dtoUtils.fromDTO(bookDTO, Book::class.java)

        book.id = null
        book.customer = customer

        book = bookRepository.save(book)

        return dtoUtils.toDTO(book, BookDTO::class.java)
    }

    /*override fun getAll(): List<BookDTO> {
        return bookRepository.findAll()
            .map { book -> dtoUtils.toDTO(book, BookDTO::class.java) }
    }*/

    override fun getAll(pageable: Pageable): Page<BookDTO> {
        return bookRepository.findAll(pageable)
            .map { book -> dtoUtils.toDTO(book, BookDTO::class.java) }
    }

    override fun getById(id: Long): Book {
        var opt: Optional<Book> = bookRepository.findById(id)
        if (opt.isEmpty){
            throw EntityNotFoundException("Book not found!")
        }
        return opt.get()
    }

    override fun getActives(): List<BookDTO> {
        return bookRepository.findByStatus(BookStatus.ATIVO)
            .map { book -> dtoUtils.toDTO(book, BookDTO::class.java) }
    }

    override fun delete(id: Long) {
        var book = getById(id)
        book.status = BookStatus.CANCELADO
        bookRepository.save(book)
    }

    override fun update(id: Long, updatedBook: UpdateBookDTO): BookDTO {
        var bookDatabase: Book = getById(id);
        BeanUtils.copyProperties(updatedBook, bookDatabase, "id", "status", "customer")
        return dtoUtils.toDTO(bookRepository.save(bookDatabase), BookDTO::class.java)
    }

    override fun deleteByCustomer(customer: Customer) {
        val books = bookRepository.findByCustomer(customer)
        for (book in books){
            book.status = BookStatus.DELETADO
        }
        bookRepository.saveAll(books)
    }
}