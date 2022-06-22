package br.com.lucasladeira.mercadolivro.entities

import javax.persistence.*

@Entity
@Table(name = "tb_customer")
data class Customer(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    var name: String? = null,
    var email: String? = null,
)