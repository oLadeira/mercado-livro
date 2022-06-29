package br.com.lucasladeira.mercadolivro.security

import br.com.lucasladeira.mercadolivro.exceptions.enums.Errors
import br.com.lucasladeira.mercadolivro.exceptions.model.StandardExceptionBody
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.http.HttpStatus
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class CustomAuthenticationEntryPoint: AuthenticationEntryPoint {

    override fun commence(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authException: AuthenticationException
    ) {
        response.contentType = "application/json"
        response.status = HttpServletResponse.SC_UNAUTHORIZED
        val error = StandardExceptionBody(
            HttpStatus.UNAUTHORIZED.value(),
            Errors.ML401.message,
            null,
            Errors.ML401.code,
            null
        )
        response.outputStream.print(jacksonObjectMapper().writeValueAsString(error))
    }


}