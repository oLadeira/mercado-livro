package br.com.lucasladeira.mercadolivro.dto

import java.math.BigDecimal
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty

data class UpdateBookDTO(
    @field:NotEmpty(message = "Nome precisa ser informado!")
    var name: String?,

    @field:Email(message = "Email precisa ser válido!")
    var price: BigDecimal?
)