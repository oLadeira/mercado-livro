package br.com.lucasladeira.mercadolivro.services

import br.com.lucasladeira.mercadolivro.entities.Customer

interface CustomerService {

    fun save(customer: Customer): Customer
    fun getAll(): List<Customer>
    fun getById(id: Long): Customer
    fun update(id: Long, updatedCustomer: Customer): Customer
}