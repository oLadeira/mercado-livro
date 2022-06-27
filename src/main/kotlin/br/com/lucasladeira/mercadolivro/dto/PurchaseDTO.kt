package br.com.lucasladeira.mercadolivro.dto

import br.com.lucasladeira.mercadolivro.entities.Book
import br.com.lucasladeira.mercadolivro.entities.Customer
import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal
import java.time.LocalDateTime

data class PurchaseDTO (

    var customer: Customer? = null,
    var books: List<Book>? = null,
    var nfe: String? = null,
    var price: BigDecimal? = null,

    var createdAt: LocalDateTime? = null
)