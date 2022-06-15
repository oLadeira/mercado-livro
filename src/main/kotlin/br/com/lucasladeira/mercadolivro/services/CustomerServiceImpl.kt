package br.com.lucasladeira.mercadolivro.services

import br.com.lucasladeira.mercadolivro.entities.Customer
import br.com.lucasladeira.mercadolivro.repositories.CustomerRepository
import org.springframework.beans.BeanUtils
import org.springframework.stereotype.Service
import java.util.*
import javax.persistence.EntityNotFoundException

@Service
class CustomerServiceImpl(var customerRepository: CustomerRepository): CustomerService {

    override fun save(customer: Customer): Customer{
        return customerRepository.save(customer)
    }

    override fun getAll(): List<Customer>{
        return customerRepository.findAll()
    }

    override fun getById(id: Long): Customer{
        val opt: Optional<Customer> = customerRepository.findById(id)
        if (opt.isEmpty){
            throw EntityNotFoundException("Customer not found!")
        }
        return opt.get()
    }

    override fun update(id: Long, updatedCustomer: Customer): Customer{
        val opt: Optional<Customer> = customerRepository.findById(id)
        if (opt.isEmpty){
            throw EntityNotFoundException("Customer not found!")
        }
        val customerDB = opt.get()
        BeanUtils.copyProperties(updatedCustomer, customerDB, "id")
        return customerRepository.save(customerDB)
    }
}