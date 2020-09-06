package hr.goodapp.warsapp

import hr.goodapp.warsapp.ui.common.InputValidator
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun inputValidator_CorrectInput_returnsTrue() {
        val inputValidator = InputValidator()
        inputValidator.checkInput("")
    }
}