package br.com.lucasladeira.mercadolivro.dto

import java.math.BigDecimal

data class UpdateBookDTO(
    var name: String?,
    var price: BigDecimal?
)