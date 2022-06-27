package br.com.lucasladeira.mercadolivro.controllers

import br.com.lucasladeira.mercadolivro.dto.NewPurchaseDTO
import br.com.lucasladeira.mercadolivro.dto.PurchaseDTO
import br.com.lucasladeira.mercadolivro.services.PurchaseService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/purchases")
class PurchaseController(
    private val purchaseService: PurchaseService
) {

    @PostMapping
    fun purchase(@RequestBody newPurchaseDTO: NewPurchaseDTO): ResponseEntity<PurchaseDTO> {
        return ResponseEntity.status(HttpStatus.CREATED).body(purchaseService.save(newPurchaseDTO))
    }

}