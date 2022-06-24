package br.com.lucasladeira.mercadolivro.dto

import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty

data class UpdateCustomerDTO(
    @field:NotEmpty(message = "Nome precisa ser informado!")
    var name: String? = null,

    @field:Email(message = "Email precisa ser válido!")
    var email: String? = null
)