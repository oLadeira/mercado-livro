package br.com.lucasladeira.mercadolivro.repositories

import br.com.lucasladeira.mercadolivro.entities.Customer
import org.springframework.data.jpa.repository.JpaRepository

interface CustomerRepository : JpaRepository<Customer, Long>{
}