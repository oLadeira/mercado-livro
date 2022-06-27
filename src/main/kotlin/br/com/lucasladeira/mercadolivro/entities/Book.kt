package br.com.lucasladeira.mercadolivro.entities

import br.com.lucasladeira.mercadolivro.enums.BookStatus
import br.com.lucasladeira.mercadolivro.exceptions.enums.Errors
import br.com.lucasladeira.mercadolivro.exceptions.model.BadRequestException
import java.math.BigDecimal
import javax.persistence.*

@Entity
@Table(name = "tb_book")
data class Book(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var name: String? = null,
    var price: BigDecimal,

    @ManyToOne
    @JoinColumn(name = "customer_id")
    var customer: Customer? = null
){
    @Enumerated(EnumType.STRING)
    var status: BookStatus? = BookStatus.ATIVO
    set(value){
        if (field==BookStatus.DELETADO || field==BookStatus.CANCELADO){
            throw BadRequestException(Errors.ML102.message.format(field), Errors.ML102.code)
        }
        field = value
    }

    constructor(
        id: Long? = null,
        name: String? = null,
        price: BigDecimal,
        customer: Customer? = null,
        status: BookStatus? = BookStatus.ATIVO
    ): this(id, name, price, customer){
        this.status = status
    }
}