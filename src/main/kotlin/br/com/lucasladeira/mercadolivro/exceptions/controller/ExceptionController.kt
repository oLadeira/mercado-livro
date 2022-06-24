package br.com.lucasladeira.mercadolivro.exceptions.controller

import br.com.lucasladeira.mercadolivro.exceptions.enums.Errors
import br.com.lucasladeira.mercadolivro.exceptions.model.BadRequestException
import br.com.lucasladeira.mercadolivro.exceptions.model.FieldError
import br.com.lucasladeira.mercadolivro.exceptions.model.NotFoundException
import br.com.lucasladeira.mercadolivro.exceptions.model.StandardExceptionBody
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import java.time.LocalDateTime

@ControllerAdvice
class ExceptionController{

    @ExceptionHandler(NotFoundException::class)
    fun entityNotFoundException(ex: NotFoundException, request: WebRequest): ResponseEntity<StandardExceptionBody>{
        val error = StandardExceptionBody(
            HttpStatus.NOT_FOUND.value(),
            ex.message.toString(),
            LocalDateTime.now(),
            ex.errorCode,
            null)
        return ResponseEntity.status(error.httpCode).body(error)
    }

    @ExceptionHandler(BadRequestException::class)
    fun badRequestException(ex: BadRequestException, request: WebRequest): ResponseEntity<StandardExceptionBody>{
        val error = StandardExceptionBody(
            HttpStatus.BAD_REQUEST.value(),
            ex.message.toString(),
            LocalDateTime.now(),
            ex.errorCode,
            null
        )
        return ResponseEntity.status(error.httpCode).body(error)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun methodArgumentNotValidException(ex: MethodArgumentNotValidException, request: WebRequest): ResponseEntity<StandardExceptionBody>{
        val error = StandardExceptionBody(
            HttpStatus.UNPROCESSABLE_ENTITY.value(),
            Errors.ML001.message,
            LocalDateTime.now(),
            Errors.ML001.code,
            ex.fieldErrors.map { fieldError ->  FieldError(fieldError.defaultMessage.toString() ,fieldError.field) }
        )
        return ResponseEntity.status(error.httpCode).body(error)
    }
}