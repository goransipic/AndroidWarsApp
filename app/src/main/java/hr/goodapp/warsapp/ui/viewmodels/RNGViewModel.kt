package hr.goodapp.warsapp.ui.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import hr.goodapp.warsapp.data.prefs.IPreferenceStorage
import hr.goodapp.warsapp.data.randomnumbers.IRandomNumbersDataSource

class RNGViewModel @ViewModelInject constructor(
    private val randomGenerator: IRandomNumbersDataSource
) : ViewModel() {

    private val generateNumber : MutableLiveData<Boolean> = MutableLiveData()
    val viewState = generateNumber.switchMap { liveData { emit(randomGenerator.getNextRandomNumber()) } }

    fun generateNextNumber() = generateNumber.postValue(true)
    fun getLastNumber() = randomGenerator.getLastGeneratedNumber()

}