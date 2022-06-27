package br.com.lucasladeira.mercadolivro.services

import br.com.lucasladeira.mercadolivro.dto.NewPurchaseDTO
import br.com.lucasladeira.mercadolivro.dto.PurchaseDTO

interface PurchaseService {

    fun save(newPurchaseDTO: NewPurchaseDTO): PurchaseDTO
}