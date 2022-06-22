package br.com.lucasladeira.mercadolivro.dto

import com.fasterxml.jackson.annotation.JsonAlias
import java.math.BigDecimal

data class NewBookDTO(
    var name: String,
    var price: BigDecimal,

    @JsonAlias("customer_id")
    var customerId: Long
)