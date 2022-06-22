package br.com.lucasladeira.mercadolivro.controllers

import br.com.lucasladeira.mercadolivro.dto.BookDTO
import br.com.lucasladeira.mercadolivro.dto.NewBookDTO
import br.com.lucasladeira.mercadolivro.entities.Book
import br.com.lucasladeira.mercadolivro.services.BookService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("books")
class BookController(
    var bookService: BookService
) {

    @PostMapping
    fun save(@RequestBody bookDTO: NewBookDTO): ResponseEntity<BookDTO>{
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.save(bookDTO))
    }

    @GetMapping
    fun getAll(): ResponseEntity<List<BookDTO>>{
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getAll())
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<Book>{
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getById(id))
    }

    @GetMapping("/actives")
    fun getActives(): ResponseEntity<List<BookDTO>>{
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getActives())
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void>{
        bookService.delete(id)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }
}