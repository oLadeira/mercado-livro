package br.com.lucasladeira.mercadolivro.dto

import com.fasterxml.jackson.annotation.JsonAlias
import java.math.BigDecimal
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

data class NewBookDTO(

    @field:NotEmpty(message = "Nome precisa ser informado!")
    var name: String,

    @field:NotNull(message = "Preço precisa ser informado!")
    var price: BigDecimal,

    @JsonAlias("customer_id")
    var customerId: Long
)