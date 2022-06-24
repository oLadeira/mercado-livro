package br.com.lucasladeira.mercadolivro.dto

import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty

data class NewCustomerDTO(
    @field:NotEmpty(message = "Nome deve ser informado!")
    val name: String,

    @field:Email(message = "E-mail deve ser válido!")
    val email: String
)