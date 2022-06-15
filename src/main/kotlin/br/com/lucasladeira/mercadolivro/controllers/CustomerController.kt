package br.com.lucasladeira.mercadolivro.controllers

import br.com.lucasladeira.mercadolivro.entities.Customer
import br.com.lucasladeira.mercadolivro.services.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/customers")
class CustomerController(var customerService: CustomerService) {

    @PostMapping
    fun save(@RequestBody customer: Customer): ResponseEntity<Customer>{
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.save(customer))
    }

    @GetMapping
    fun getAll(): ResponseEntity<List<Customer>>{
        return ResponseEntity.status(HttpStatus.OK).body(customerService.getAll())
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<Customer>{
        return ResponseEntity.status(HttpStatus.OK).body(customerService.getById(id))
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody customer: Customer): ResponseEntity<Customer>{
        return ResponseEntity.status(HttpStatus.OK).body(customerService.update(id, customer))
    }
}