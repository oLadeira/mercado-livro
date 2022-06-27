package br.com.lucasladeira.mercadolivro.controllers

import br.com.lucasladeira.mercadolivro.dto.NewPurchaseDTO
import br.com.lucasladeira.mercadolivro.dto.PurchaseDTO
import br.com.lucasladeira.mercadolivro.services.PurchaseService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
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

    @GetMapping("/{customerId}")
    fun getAllPurchasesByCustomerId(@PathVariable(name = "customerId") customerId: Long, pageable: Pageable): ResponseEntity<Page<PurchaseDTO>>{
        return ResponseEntity.status(HttpStatus.OK).body(purchaseService.getAllPurchasesByCustomerId(customerId, pageable))
    }

    @GetMapping
    fun getAllPurchases(pageable: Pageable): ResponseEntity<Page<PurchaseDTO>>{
        return ResponseEntity.status(HttpStatus.OK).body(purchaseService.getAllPurchases(pageable))
    }
}