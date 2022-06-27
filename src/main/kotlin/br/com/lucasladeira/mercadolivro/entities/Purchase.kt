package br.com.lucasladeira.mercadolivro.entities

import java.math.BigDecimal
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "tb_purchase")
data class Purchase(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @ManyToOne
    @JoinColumn(name = "customer_id")
    var customer: Customer,

    @ManyToMany
    @JoinTable(name = "purchase_book",
    joinColumns = [JoinColumn(name = "purchase_id")],
    inverseJoinColumns = [JoinColumn(name = "book_id")])
    var books: MutableList<Book>,

    var nfe: String? = null,

    var price: BigDecimal,

    @Column(name = "created_at")
    var createdAt: LocalDateTime = LocalDateTime.now()
)