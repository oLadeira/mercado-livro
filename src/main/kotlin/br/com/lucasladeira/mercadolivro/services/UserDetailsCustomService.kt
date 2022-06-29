package br.com.lucasladeira.mercadolivro.services

import br.com.lucasladeira.mercadolivro.exceptions.model.AuthenticationException
import br.com.lucasladeira.mercadolivro.repositories.CustomerRepository
import br.com.lucasladeira.mercadolivro.security.UserCustomDetails
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserDetailsCustomService(
    private val customerRepository: CustomerRepository
): UserDetailsService {

    override fun loadUserByUsername(id: String): UserDetails {
        val customer = customerRepository
            .findById(id.toLong())
            .orElseThrow { AuthenticationException("Usuario não encontrado", "")}

        return UserCustomDetails(customer)
    }
}