package br.com.lucasladeira.mercadolivro.services

import br.com.lucasladeira.mercadolivro.entities.Customer
import br.com.lucasladeira.mercadolivro.repositories.CustomerRepository
import org.springframework.stereotype.Service
import java.util.Optional
import javax.persistence.EntityNotFoundException

@Service
class CustomerService(var customerRepository: CustomerRepository) {

    fun save(customer: Customer): Customer{
        return customerRepository.save(customer)
    }

    fun getAll(): List<Customer>{
        return customerRepository.findAll()
    }

    fun getById(id: Long): Customer{
        val opt: Optional<Customer> = customerRepository.findById(id)
        if (opt.isEmpty){
            throw EntityNotFoundException("Customer not found!")
        }
        return opt.get()
    }
}