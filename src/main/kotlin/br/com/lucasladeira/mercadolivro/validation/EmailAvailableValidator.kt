package br.com.lucasladeira.mercadolivro.validation

import br.com.lucasladeira.mercadolivro.services.CustomerService
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class EmailAvailableValidator(var customerService: CustomerService): ConstraintValidator<EmailAvailable, String> {

    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {
        if (value.isNullOrEmpty()){
            return false
        }
        return customerService.emailAvaliable(value)
    }

}
