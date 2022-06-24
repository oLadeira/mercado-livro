package br.com.lucasladeira.mercadolivro.services

import br.com.lucasladeira.mercadolivro.dto.CustomerDTO
import br.com.lucasladeira.mercadolivro.dto.NewCustomerDTO
import br.com.lucasladeira.mercadolivro.dto.UpdateCustomerDTO
import br.com.lucasladeira.mercadolivro.entities.Customer
import br.com.lucasladeira.mercadolivro.enums.CustomerStatus
import br.com.lucasladeira.mercadolivro.exceptions.enums.Errors
import br.com.lucasladeira.mercadolivro.exceptions.model.NotFoundException
import br.com.lucasladeira.mercadolivro.repositories.CustomerRepository
import br.com.lucasladeira.mercadolivro.utils.DTOUtils
import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class CustomerServiceImpl(
    var customerRepository: CustomerRepository,
    ): CustomerService {

    @Autowired
    lateinit var mapper: DTOUtils

    override fun save(customer: NewCustomerDTO): CustomerDTO{
        var customerSave: Customer = mapper.fromDTO(customer, Customer::class.java)
        customerSave = customerRepository.save(customerSave)
        return mapper.toDTO(customerSave, CustomerDTO::class.java)
    }

    override fun getAll(): List<CustomerDTO>{
        return customerRepository
            .findAll()
            .map { customer ->  mapper.toDTO(customer, CustomerDTO::class.java) }
    }

    override fun getById(id: Long): Customer{
        val opt: Optional<Customer> = customerRepository.findById(id)
        if (opt.isEmpty){
            throw NotFoundException(Errors.ML201.message.format(id), Errors.ML201.code)
        }
        return opt.get()
    }

    override fun update(id: Long, updatedCustomer: UpdateCustomerDTO): CustomerDTO{
        val customer: Customer = getById(id)

        BeanUtils.copyProperties(updatedCustomer, customer, "id")
        return mapper.toDTO(customerRepository.save(customer), CustomerDTO::class.java)
    }

    override fun delete(id: Long) {
        var customer: Customer = getById(id)
        customer.status = CustomerStatus.INATIVO
        customerRepository.save(customer)
    }

    override fun emailAvaliable(email: String): Boolean {
        return !customerRepository.existsByEmail(email)
    }
}