package br.com.lucasladeira.mercadolivro.exceptions.model

import java.time.LocalDateTime

data class StandardExceptionBody(
    var httpCode: Int,
    var message: String,
    var timeStamp: LocalDateTime?,
    var internalCode: String,
    var errors: List<FieldError>?
)