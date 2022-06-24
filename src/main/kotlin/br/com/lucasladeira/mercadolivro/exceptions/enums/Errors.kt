package br.com.lucasladeira.mercadolivro.exceptions.enums

enum class Errors(
    val code: String,
    val message: String
) {
    ML101("ML-101", "Book %s not exists!"),
    ML102("ML-102", "Cannot delete Book with status %s!"),
    ML201("ML-201", "Customer %s not exists!")
}