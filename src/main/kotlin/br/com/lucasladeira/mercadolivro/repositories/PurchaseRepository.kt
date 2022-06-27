package br.com.lucasladeira.mercadolivro.repositories

import br.com.lucasladeira.mercadolivro.entities.Purchase
import org.springframework.data.jpa.repository.JpaRepository

interface PurchaseRepository: JpaRepository<Purchase, Long> {
}