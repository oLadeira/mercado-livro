package br.com.lucasladeira.mercadolivro.services

import br.com.lucasladeira.mercadolivro.dto.NewPurchaseDTO
import br.com.lucasladeira.mercadolivro.dto.PurchaseDTO
import br.com.lucasladeira.mercadolivro.entities.Purchase
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface PurchaseService {

    fun save(newPurchaseDTO: NewPurchaseDTO): PurchaseDTO
    fun update(purchase: Purchase)
    fun getAllPurchasesByCustomerId(customerId: Long, pageable: Pageable): Page<PurchaseDTO>
}