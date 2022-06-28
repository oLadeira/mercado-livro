package br.com.lucasladeira.mercadolivro.entities

import br.com.lucasladeira.mercadolivro.enums.CustomerStatus
import br.com.lucasladeira.mercadolivro.enums.Profile
import javax.persistence.*

@Entity
@Table(name = "tb_customer")
data class Customer(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    var name: String? = null,
    var email: String? = null,
    var status: CustomerStatus? = CustomerStatus.ATIVO,
    var password: String,

    @CollectionTable(name = "tb_customer_roles",
    joinColumns = [JoinColumn(name= "customer_id")])
    @ElementCollection(targetClass = Profile::class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    var roles: Set<Profile> = setOf()
)