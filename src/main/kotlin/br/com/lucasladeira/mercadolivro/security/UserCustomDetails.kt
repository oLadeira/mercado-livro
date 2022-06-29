package br.com.lucasladeira.mercadolivro.security

import br.com.lucasladeira.mercadolivro.entities.Customer
import br.com.lucasladeira.mercadolivro.enums.CustomerStatus
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UserCustomDetails(val customer: Customer): UserDetails {

    val id = customer.id

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return customer.roles
            .map { role -> SimpleGrantedAuthority(role.description)}.toMutableList()
    }

    override fun getPassword(): String = customer.password

    override fun getUsername(): String = customer.id.toString()

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = customer.status == CustomerStatus.ATIVO
}