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
    val customer: Customer,

    @ManyToMany
    @JoinTable(name = "purchase_book",
    joinColumns = [JoinColumn(name = "purchase_id")],
    inverseJoinColumns = [JoinColumn(name = "book_id")])
    val books: List<Book>,

    val nfe: String? = null,

    val price: BigDecimal,

    @Column(name = "created_at")
    val createdAt: LocalDateTime
)