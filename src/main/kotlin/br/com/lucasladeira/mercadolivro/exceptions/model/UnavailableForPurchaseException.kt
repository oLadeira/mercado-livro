package br.com.lucasladeira.mercadolivro.exceptions.model

class UnavailableForPurchaseException(
    override var message: String,
    var errorCode: String
): Exception(){
}