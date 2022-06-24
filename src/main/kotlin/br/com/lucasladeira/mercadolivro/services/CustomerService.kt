package br.com.lucasladeira.mercadolivro.services

import br.com.lucasladeira.mercadolivro.dto.CustomerDTO
import br.com.lucasladeira.mercadolivro.dto.NewCustomerDTO
import br.com.lucasladeira.mercadolivro.dto.UpdateCustomerDTO
import br.com.lucasladeira.mercadolivro.entities.Customer

interface CustomerService {

    fun save(customer: NewCustomerDTO): CustomerDTO
    fun getAll(): List<CustomerDTO>
    fun getById(id: Long): Customer
    fun update(id: Long, updatedCustomer: UpdateCustomerDTO): CustomerDTO
    fun delete(id: Long)
}