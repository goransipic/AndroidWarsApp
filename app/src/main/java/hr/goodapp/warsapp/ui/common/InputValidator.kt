package hr.goodapp.warsapp.ui.common

import java.lang.IllegalStateException
import javax.inject.Inject

// TODO Write Tests

class InputValidator @Inject constructor (private val validators : InputValidatorList) {

    fun checkInput(input : String) : ValidationResult {

        for((index,validator) in validators.listOfValidators.withIndex()){
            val result = validator.checkInput(input)
            if (!result.validationSuccess){
                return result
            }
            if(index == validators.listOfValidators.size - 1){
                return result
            }
        }

        throw IllegalStateException("Not Allowed State")
    }
}
// TODO Better HILT INJECTION
class InputValidatorList @Inject constructor () {
    val listOfValidators = listOf(
    InputLengthValidator(),
    RepeatOfCharacterValidator(),
    IsDigitValidator(), CheckIsExistValidator(), OneAfterOtherValidator()
    )
}

interface ICheckInput{
    fun checkInput(input : String ) : ValidationResult
}
// TODO WRITE validation Message
class InputLengthValidator : ICheckInput {
    override fun checkInput(input: String): ValidationResult {
        return ValidationResult(input.length in 5..13, "")
    }
}

class RepeatOfCharacterValidator : ICheckInput {
    override fun checkInput(input: String): ValidationResult {
        return ValidationResult(input.toCharArray().count { it == 'A' || it == 'a' } >= 2, "")
    }
}

class IsDigitValidator : ICheckInput {
    override fun checkInput(input: String): ValidationResult {
        return ValidationResult(input.toCharArray().count { it.isDigit() } == 1, "")
    }
}

class CheckIsExistValidator : ICheckInput {
    override fun checkInput(input: String): ValidationResult {
        return ValidationResult(!input.contains('?'), "")
    }
}

class OneAfterOtherValidator : ICheckInput {
    override fun checkInput(input: String): ValidationResult {
        if (input.contains('b')){
           val index = input.indexOf('b')
           if (index > 1){
               return ValidationResult(!input[index].isDigit(),"")
           }
            if (index == 0){
                return ValidationResult(true,"")
            }
        }
        return ValidationResult(true, "")
    }
}

data class ValidationResult( val validationSuccess : Boolean, val validationMessage: String? = null)