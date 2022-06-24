package br.com.lucasladeira.mercadolivro.controllers

import br.com.lucasladeira.mercadolivro.dto.CustomerDTO
import br.com.lucasladeira.mercadolivro.dto.NewCustomerDTO
import br.com.lucasladeira.mercadolivro.dto.UpdateCustomerDTO
import br.com.lucasladeira.mercadolivro.entities.Customer
import br.com.lucasladeira.mercadolivro.services.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/customers")
class CustomerController(var customerService: CustomerService) {

    @PostMapping
    fun save(@RequestBody @Valid customer: NewCustomerDTO): ResponseEntity<CustomerDTO>{
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.save(customer))
    }

    @GetMapping
    fun getAll(): ResponseEntity<List<CustomerDTO>>{
        return ResponseEntity.status(HttpStatus.OK).body(customerService.getAll())
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<Customer>{
        return ResponseEntity.status(HttpStatus.OK).body(customerService.getById(id))
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody @Valid customer: UpdateCustomerDTO): ResponseEntity<CustomerDTO>{
        return ResponseEntity.status(HttpStatus.OK).body(customerService.update(id, customer))
    }

    @DeleteMapping("/{id}")
    fun delete (@PathVariable id: Long): ResponseEntity<Void>{
        customerService.delete(id)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }
}