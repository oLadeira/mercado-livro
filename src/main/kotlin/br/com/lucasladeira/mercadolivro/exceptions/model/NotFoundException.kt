package br.com.lucasladeira.mercadolivro.exceptions.model

class NotFoundException(
    override var message: String,
    var errorCode: String
    ): Exception(){
}