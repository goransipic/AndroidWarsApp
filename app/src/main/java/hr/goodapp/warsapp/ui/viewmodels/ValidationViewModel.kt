package hr.goodapp.warsapp.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ValidationViewModel : ViewModel() {

    val viewStateData = MutableLiveData<CurrentLanguage>()

    init {
        viewStateData.postValue(CurrentLanguage(Language.DEFAULT))
    }
}

sealed class ValidationViewState

data class CurrentLanguage(val selectedLanguage : Language) : ValidationViewState()

enum class Language(val language : String) {
    DEFAULT("-"),ENGLISH("English"), CROATIAN("Croatian")
}