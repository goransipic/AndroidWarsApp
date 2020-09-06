package hr.goodapp.warsapp.data.randomnumbers

interface IRandomNumbersDataSource {
    fun getNextRandomNumber() : String
    fun getLastGeneratedNumber(): String
}