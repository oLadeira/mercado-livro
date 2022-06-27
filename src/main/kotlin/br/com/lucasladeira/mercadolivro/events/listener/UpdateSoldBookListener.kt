package br.com.lucasladeira.mercadolivro.events.listener

import br.com.lucasladeira.mercadolivro.events.PurchaseEvent
import br.com.lucasladeira.mercadolivro.services.BookService
import org.slf4j.LoggerFactory
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component

@Component
class UpdateSoldBookListener(
    private val bookService: BookService
) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    @Async //tornando o processamento assincrono
    @EventListener
    fun listen(purchaseEvent: PurchaseEvent){
        logger.info("Atualizando status do livro para VENDIDO!")
        bookService.purchase(purchaseEvent.purchase.books)
    }
}