package br.com.lucasladeira.mercadolivro.dto

import br.com.lucasladeira.mercadolivro.validation.EmailAvailable
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty

data class NewCustomerDTO(
    @field:NotEmpty(message = "Nome deve ser informado!")
    var name: String,

    @field:Email(message = "E-mail deve ser válido!")
    @EmailAvailable
    var email: String,

    @field:NotEmpty(message = "Senha deve ser informado!")
    var password: String
)