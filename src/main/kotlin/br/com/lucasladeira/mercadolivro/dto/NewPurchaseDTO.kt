package br.com.lucasladeira.mercadolivro.dto

import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

data class NewPurchaseDTO(

    @field:NotNull
    @field:Positive
    val customerId: Long,

    @field:NotNull
    val bookIds: Set<Long>
)
