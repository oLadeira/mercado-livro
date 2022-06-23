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

    @ManyToOne
    @JoinColumn(name = "customer_id")
    var customer: Customer? = null
){
    @Enumerated(EnumType.STRING)
    var status: BookStatus? = BookStatus.ATIVO
    set(value){
        if (value==BookStatus.DELETADO || value==BookStatus.CANCELADO){
            throw Exception("Não é possível deletar um livro com o status ${field}")
        }
        field = value
    }

    constructor(
        id: Long? = null,
        name: String? = null,
        price: BigDecimal? = null,
        customer: Customer? = null,
        status: BookStatus? = BookStatus.ATIVO
    ): this(id, name, price, customer){
        this.status = status
    }
}