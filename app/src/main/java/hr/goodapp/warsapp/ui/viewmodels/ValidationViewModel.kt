package hr.goodapp.warsapp.ui.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import hr.goodapp.warsapp.data.prefs.IPreferenceStorage
import hr.goodapp.warsapp.ui.common.ICheckInput
import hr.goodapp.warsapp.ui.common.InputValidator

class ValidationViewModel @ViewModelInject constructor(
    private val IPreferenceStorage: IPreferenceStorage,
    private val checkInput: InputValidator,
) : ViewModel()  {

    private val refreshLanguage: MutableLiveData<Boolean> = MutableLiveData()
    val viewStateData = refreshLanguage.switchMap { liveData { emit(CurrentLanguage(IPreferenceStorage.currentLanguage)) } }

    fun refreshLanguage() {
        refreshLanguage.value = true
    }

    fun checkInput(input : String) : Boolean {
        return checkInput.checkInput(input).validationSuccess
    }
}

sealed class ValidationViewState

data class CurrentLanguage(val selectedLanguage : String) : ValidationViewState()
