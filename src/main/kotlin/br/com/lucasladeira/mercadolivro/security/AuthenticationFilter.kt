package br.com.lucasladeira.mercadolivro.security

import br.com.lucasladeira.mercadolivro.dto.LoginDTO
import br.com.lucasladeira.mercadolivro.exceptions.model.AuthenticationException
import br.com.lucasladeira.mercadolivro.repositories.CustomerRepository
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AuthenticationFilter(
    authenticationManager: AuthenticationManager,
    private val customerRepository: CustomerRepository,
    private val jwtUtil: JwtUtil
): UsernamePasswordAuthenticationFilter(authenticationManager) {

    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse): Authentication {
        try {
            val credentials = jacksonObjectMapper()
                            .readValue(request.inputStream, LoginDTO::class.java)

            val customerId = customerRepository.findByEmail(credentials.email)?.id
            val authToken = UsernamePasswordAuthenticationToken(customerId, credentials.password)
            return authenticationManager.authenticate(authToken)
        }catch (ex: Exception){
            throw AuthenticationException("Falha ao autenticar!", "999")
        }
    }

    override fun successfulAuthentication(
        request: HttpServletRequest,
        response: HttpServletResponse,
        chain: FilterChain,
        authResult: Authentication
    ){
        val id = (authResult.principal as UserCustomDetails).id
        val token = jwtUtil.generateToken(id.toString())
        response.addHeader("Authorization", "Bearer $token")
    }

}