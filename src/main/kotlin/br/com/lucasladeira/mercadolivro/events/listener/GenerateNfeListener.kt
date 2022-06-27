package br.com.lucasladeira.mercadolivro.events.listener

import br.com.lucasladeira.mercadolivro.events.PurchaseEvent
import br.com.lucasladeira.mercadolivro.services.PurchaseService
import org.slf4j.LoggerFactory
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import java.util.*

@Component
class GenerateNfeListener(
    private val purchaseService: PurchaseService
) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    @Async //tornando o processamento assincrono
    @EventListener
    fun listener(purchaseEvent: PurchaseEvent){
        logger.info("Gerando nota fiscal!")
        val nfe = UUID.randomUUID().toString()

        //criando uma instancia com nfe preenchida
        val purchase = purchaseEvent.purchase.copy(nfe = nfe)

        purchaseService.update(purchase)
    }
}