package br.com.lucasladeira.mercadolivro.services

import br.com.lucasladeira.mercadolivro.dto.NewPurchaseDTO
import br.com.lucasladeira.mercadolivro.dto.PurchaseDTO
import br.com.lucasladeira.mercadolivro.entities.Purchase
import br.com.lucasladeira.mercadolivro.events.PurchaseEvent
import br.com.lucasladeira.mercadolivro.repositories.PurchaseRepository
import br.com.lucasladeira.mercadolivro.utils.DTOUtils
import org.slf4j.LoggerFactory
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import javax.transaction.Transactional

@Service
class PurchaseServiceImpl(
    private val purchaseRepository: PurchaseRepository,
    private val customerService: CustomerService,
    private val bookService: BookService,
    private val mapper: DTOUtils,
    private val applicationEventPublisher: ApplicationEventPublisher
    ): PurchaseService {

    private val logger = LoggerFactory.getLogger(this::class.java)

    @Transactional
    override fun save(newPurchaseDTO: NewPurchaseDTO): PurchaseDTO {
        var purchase = mapper.fromDTO(newPurchaseDTO, Purchase::class.java)
        val customer = customerService.getById(newPurchaseDTO.customerId)
        val books = bookService.getAllById(newPurchaseDTO.bookIds)

        //validando se os livros estao disponiveis para compra
        bookService.avaliableForPurchase(books)

        purchase.id = null
        purchase.customer = customer
        purchase.books = books.toMutableList()
        purchase.price = books.sumOf { it.price }
        purchase.createdAt = LocalDateTime.now()

        purchase = purchaseRepository.save(purchase)

        //disparando evento
        logger.info("Disparando evento de compra!")
        applicationEventPublisher.publishEvent(PurchaseEvent(this, purchase))

        return mapper.toDTO(purchase, PurchaseDTO::class.java)
    }

    override fun update(purchase: Purchase) {
        purchaseRepository.save(purchase)
    }
}