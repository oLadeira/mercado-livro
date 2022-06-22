package br.com.lucasladeira.mercadolivro.controllers

import br.com.lucasladeira.mercadolivro.dto.BookDTO
import br.com.lucasladeira.mercadolivro.dto.NewBookDTO
import br.com.lucasladeira.mercadolivro.services.BookService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
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

}