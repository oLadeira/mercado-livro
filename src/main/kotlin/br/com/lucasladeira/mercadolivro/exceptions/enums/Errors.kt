package br.com.lucasladeira.mercadolivro.exceptions.enums

enum class Errors(
    val code: String,
    val message: String
) {
    ML001("ML-001", "Invalid request"),

    //Book exceptions
    ML101("ML-101", "Book %s not exists!"),
    ML102("ML-102", "Cannot delete Book with status %s!"),
    ML103("ML-103", "Cannot purchase Book %s with status %s!"),

    //Customer exceptions
    ML201("ML-201", "Customer %s not exists!")
}