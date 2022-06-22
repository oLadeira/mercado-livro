package br.com.lucasladeira.mercadolivro.entities

import br.com.lucasladeira.mercadolivro.enums.BookStatus
import java.math.BigDecimal
import javax.persistence.*

@Entity
@Table(name = "tb_book")
data class Book(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var name: String? = null,
    var price: BigDecimal? = null,
    @Enumerated(EnumType.STRING)
    var status: BookStatus? = BookStatus.ATIVO,
    @ManyToOne
    @JoinColumn(name = "customer_id")
    var customer: Customer? = null
)