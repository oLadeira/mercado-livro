package br.com.lucasladeira.mercadolivro.repositories

import br.com.lucasladeira.mercadolivro.entities.Customer
import br.com.lucasladeira.mercadolivro.entities.Purchase
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface PurchaseRepository: JpaRepository<Purchase, Long> {

    fun findByCustomer(customer: Customer, pageable: Pageable): Page<Purchase>
}