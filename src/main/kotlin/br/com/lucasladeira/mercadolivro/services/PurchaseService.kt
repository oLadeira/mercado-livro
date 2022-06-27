package br.com.lucasladeira.mercadolivro.services

import br.com.lucasladeira.mercadolivro.dto.NewPurchaseDTO
import br.com.lucasladeira.mercadolivro.dto.PurchaseDTO
import br.com.lucasladeira.mercadolivro.entities.Purchase

interface PurchaseService {

    fun save(newPurchaseDTO: NewPurchaseDTO): PurchaseDTO
    fun update(purchase: Purchase)
}