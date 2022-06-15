package br.com.lucasladeira.mercadolivro.services

import br.com.lucasladeira.mercadolivro.entities.Customer
import br.com.lucasladeira.mercadolivro.repositories.CustomerRepository
import org.springframework.stereotype.Service

@Service
class CustomerService(var customerRepository: CustomerRepository) {

    fun save(customer: Customer): Customer{
        return customerRepository.save(customer)
    }
}