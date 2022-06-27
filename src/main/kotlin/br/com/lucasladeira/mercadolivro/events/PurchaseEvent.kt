package br.com.lucasladeira.mercadolivro.events

import br.com.lucasladeira.mercadolivro.entities.Purchase
import org.springframework.context.ApplicationEvent

class PurchaseEvent (
    source: Any,
    val purchase: Purchase
): ApplicationEvent(source)