package br.com.lucasladeira.mercadolivro.services

import br.com.lucasladeira.mercadolivro.dto.NewPurchaseDTO
import br.com.lucasladeira.mercadolivro.dto.PurchaseDTO
import br.com.lucasladeira.mercadolivro.entities.Purchase
import br.com.lucasladeira.mercadolivro.events.PurchaseEvent
import br.com.lucasladeira.mercadolivro.repositories.PurchaseRepository
import br.com.lucasladeira.mercadolivro.utils.DTOUtils
import org.slf4j.LoggerFactory
import org.springframework.context.ApplicationEventPublisher
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
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

    //@Transactional
    override fun save(newPurchaseDTO: NewPurchaseDTO): PurchaseDTO {
        val customer = customerService.getById(newPurchaseDTO.customerId)
        val books = bookService.getAllById(newPurchaseDTO.bookIds)

        //validando se os livros estao disponiveis para compra
        bookService.avaliableForPurchase(books)

        var purchase = Purchase(
            null,
            customer,
            books.toMutableList(),
            null,
            bookService.calculateTotal(books),
            LocalDateTime.now())

        purchase = purchaseRepository.save(purchase)
        logger.info("Salvando compra com id = ${purchase.id}")
        //disparando evento
        logger.info("Disparando evento de compra!")
        applicationEventPublisher.publishEvent(PurchaseEvent(this, purchase))

        return mapper.toDTO(purchase, PurchaseDTO::class.java)
    }

    override fun update(purchase: Purchase) {
        logger.info("ID da compra ${purchase.id}")
        purchaseRepository.save(purchase)
    }

    override fun getAllPurchasesByCustomerId(customerId: Long, pageable: Pageable): Page<PurchaseDTO> {
        val customer = customerService.getById(customerId)
        return purchaseRepository
            .findByCustomer(customer, pageable)
            .map { purchase ->  mapper.toDTO(purchase, PurchaseDTO::class.java) }
    }
}