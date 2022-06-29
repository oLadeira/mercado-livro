package br.com.lucasladeira.mercadolivro.exceptions.model

class AuthenticationException(
    override var message: String,
    var errorCode: String
    ): Exception(){
}