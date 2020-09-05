package hr.goodapp.warsapp.ui.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import hr.goodapp.warsapp.data.prefs.PreferenceStorage

class ValidationViewModel @ViewModelInject constructor(
    private val preferenceStorage: PreferenceStorage,
) : ViewModel()  {

    private val refreshLanguage: MutableLiveData<Boolean> = MutableLiveData()
    val viewStateData = refreshLanguage.switchMap { liveData { emit(CurrentLanguage(preferenceStorage.currentLanguage)) } }

    fun refreshLanguage() {
        refreshLanguage.value = true
    }
}

sealed class ValidationViewState

data class CurrentLanguage(val selectedLanguage : String) : ValidationViewState()
