package br.com.lucasladeira.mercadolivro.controllers

import br.com.lucasladeira.mercadolivro.dto.NewPurchaseDTO
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController("/purchase")
class PurchaseController {

    @PostMapping
    fun purchase(@RequestBody purchase: NewPurchaseDTO) {

    }

}