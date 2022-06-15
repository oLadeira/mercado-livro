package br.com.lucasladeira.mercadolivro.controllers

import br.com.lucasladeira.mercadolivro.entities.Customer
import br.com.lucasladeira.mercadolivro.services.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/customers")
class CustomerController(var customerService: CustomerService) {

    @PostMapping
    fun save(@RequestBody customer: Customer): ResponseEntity<Customer>{
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.save(customer))
    }

}