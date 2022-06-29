package br.com.lucasladeira.mercadolivro.services

import br.com.lucasladeira.mercadolivro.dto.BookDTO
import br.com.lucasladeira.mercadolivro.dto.NewBookDTO
import br.com.lucasladeira.mercadolivro.dto.UpdateBookDTO
import br.com.lucasladeira.mercadolivro.entities.Book
import br.com.lucasladeira.mercadolivro.entities.Customer
import br.com.lucasladeira.mercadolivro.enums.BookStatus
import br.com.lucasladeira.mercadolivro.exceptions.enums.Errors
import br.com.lucasladeira.mercadolivro.exceptions.model.NotFoundException
import br.com.lucasladeira.mercadolivro.exceptions.model.UnavailableForPurchaseException
import br.com.lucasladeira.mercadolivro.repositories.BookRepository
import br.com.lucasladeira.mercadolivro.utils.DTOUtils
import org.springframework.beans.BeanUtils
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.util.*

@Service
class BookServiceImpl(
    private var bookRepository: BookRepository,
    private var customerService: CustomerService,
    private var dtoUtils: DTOUtils,
): BookService {


    override fun save(bookDTO: NewBookDTO): BookDTO {
        val customer: Customer = customerService.getById(bookDTO.customerId)
        var book: Book = dtoUtils.fromDTO(bookDTO, Book::class.java)

        book.id = null
        book.customer = customer

        book = bookRepository.save(book)

        return dtoUtils.toDTO(book, BookDTO::class.java)
    }

    override fun getAll(pageable: Pageable): Page<BookDTO> {
        return bookRepository.findAll(pageable)
            .map { book -> dtoUtils.toDTO(book, BookDTO::class.java) }
    }

    override fun getById(id: Long): Book {
        val opt: Optional<Book> = bookRepository.findById(id)
        if (opt.isEmpty){
            throw NotFoundException(Errors.ML101.message.format(id), Errors.ML101.code)
        }
        return opt.get()
    }

    override fun getAllById(ids: Set<Long>): List<Book> {
        val list = bookRepository.findAllById(ids).toList()
        if (list.isEmpty()){
            throw NotFoundException(Errors.ML101.message, Errors.ML101.code)
        }
        return list
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

    override fun purchase(books: MutableList<Book>) {
        books.map {
            it.status = BookStatus.VENDIDO
        }
        bookRepository.saveAll(books)
    }

    override fun avaliableForPurchase(books: List<Book>) {
        for (book in books){
            if (book.status != BookStatus.ATIVO){
                throw UnavailableForPurchaseException(Errors.ML103.message.format(book.name, book.status), Errors.ML103.code)
            }
        }
    }

    override fun calculateTotal(books: List<Book>): BigDecimal {
        return books.sumOf { it.price }
    }
}