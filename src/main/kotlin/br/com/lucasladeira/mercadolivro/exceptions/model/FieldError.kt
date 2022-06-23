package br.com.lucasladeira.mercadolivro.exceptions.model

data class FieldError(
    var message: String,
    var field: String
)
