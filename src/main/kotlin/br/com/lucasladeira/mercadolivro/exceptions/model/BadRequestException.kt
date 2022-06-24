package br.com.lucasladeira.mercadolivro.exceptions.model

class BadRequestException(
    override var message: String,
    var errorCode: String
    ): Exception(){
}