package hr.goodapp.warsapp.data.randomnumbers

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalRandomNumbersDataSource @Inject constructor() : IRandomNumbersDataSource {

    private var generatedNumber : String = ""

    override fun getNextRandomNumber() : String {
        val value = kotlin.random.Random
                .nextInt(10, 100)
                .toString()
        generatedNumber = value
        return value
        }

    override fun getLastGeneratedNumber(): String = generatedNumber

}