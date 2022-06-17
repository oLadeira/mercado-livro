package br.com.lucasladeira.mercadolivro.services

import br.com.lucasladeira.mercadolivro.dto.CustomerDTO
import br.com.lucasladeira.mercadolivro.dto.NewCustomerDTO
import br.com.lucasladeira.mercadolivro.dto.UpdateCustomerDTO
import br.com.lucasladeira.mercadolivro.entities.Customer
import br.com.lucasladeira.mercadolivro.repositories.CustomerRepository
import br.com.lucasladeira.mercadolivro.utils.DTOUtils
import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import javax.persistence.EntityNotFoundException
import kotlin.collections.ArrayList

@Service
class CustomerServiceImpl(
    var customerRepository: CustomerRepository,
    ): CustomerService {

    @Autowired
    lateinit var mapper: DTOUtils

    override fun save(customer: NewCustomerDTO): Customer{
        val customerSave: Customer = mapper.fromDTO(customer, Customer())
        return customerRepository.save(customerSave);
    }

    override fun getAll(): List<CustomerDTO>{
        return customerRepository
            .findAll()
            .map { customer ->  mapper.fromDTO(customer, CustomerDTO()) }
    }

    override fun getById(id: Long): Customer{
        val opt: Optional<Customer> = customerRepository.findById(id)
        if (opt.isEmpty){
            throw EntityNotFoundException("Customer not found!")
        }
        return opt.get()
    }

    override fun update(id: Long, updatedCustomer: UpdateCustomerDTO): CustomerDTO{
        val opt: Optional<Customer> = customerRepository.findById(id)
        if (opt.isEmpty){
            throw EntityNotFoundException("Customer not found!")
        }
        val customerDB = opt.get()
        BeanUtils.copyProperties(updatedCustomer, customerDB, "id")
        return mapper.fromDTO(customerRepository.save(customerDB), CustomerDTO())
    }
}