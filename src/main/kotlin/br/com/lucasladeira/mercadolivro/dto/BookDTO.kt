package br.com.lucasladeira.mercadolivro.dto

import br.com.lucasladeira.mercadolivro.enums.BookStatus
import java.math.BigDecimal

data class BookDTO (
    var name: String? = null,
    var price: BigDecimal? = null,
    var status: BookStatus? = null
)