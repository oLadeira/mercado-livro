package br.com.lucasladeira.mercadolivro.events.listener

import br.com.lucasladeira.mercadolivro.events.PurchaseEvent
import br.com.lucasladeira.mercadolivro.services.PurchaseService
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import java.util.*

@Component
class GenerateNfeListener(
    private val purchaseService: PurchaseService
) {

    @EventListener
    fun listener(purchaseEvent: PurchaseEvent){
        val nfe = UUID.randomUUID().toString()

        //criando uma instancia com nfe preenchida
        val purchase = purchaseEvent.purchase.copy(nfe = nfe)

        purchaseService.update(purchase)
    }
}