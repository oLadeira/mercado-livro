package br.com.lucasladeira.mercadolivro.controllers

import br.com.lucasladeira.mercadolivro.dto.BookDTO
import br.com.lucasladeira.mercadolivro.dto.NewBookDTO
import br.com.lucasladeira.mercadolivro.dto.UpdateBookDTO
import br.com.lucasladeira.mercadolivro.entities.Book
import br.com.lucasladeira.mercadolivro.services.BookService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("books")
class BookController(
    private var bookService: BookService
) {

    @PostMapping
    fun save(@RequestBody @Valid bookDTO: NewBookDTO): ResponseEntity<BookDTO>{
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.save(bookDTO))
    }

    @GetMapping
    fun getAll(@PageableDefault(size = 12, page = 0, direction = Sort.Direction.ASC) pageable: Pageable): ResponseEntity<Page<BookDTO>>{
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getAll(pageable))
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

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody @Valid bookDTO: UpdateBookDTO): ResponseEntity<BookDTO>{
        return ResponseEntity.status(HttpStatus.OK).body(bookService.update(id, bookDTO))
    }
}