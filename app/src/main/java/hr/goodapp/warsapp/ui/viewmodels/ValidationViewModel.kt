package hr.goodapp.warsapp.ui.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import hr.goodapp.warsapp.data.prefs.IPreferenceStorage

class ValidationViewModel @ViewModelInject constructor(
    private val IPreferenceStorage: IPreferenceStorage,
) : ViewModel()  {

    private val refreshLanguage: MutableLiveData<Boolean> = MutableLiveData()
    val viewStateData = refreshLanguage.switchMap { liveData { emit(CurrentLanguage(IPreferenceStorage.currentLanguage)) } }

    fun refreshLanguage() {
        refreshLanguage.value = true
    }
}

sealed class ValidationViewState

data class CurrentLanguage(val selectedLanguage : String) : ValidationViewState()
