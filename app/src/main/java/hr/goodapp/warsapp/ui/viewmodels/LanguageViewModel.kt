package hr.goodapp.warsapp.ui.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import hr.goodapp.warsapp.data.prefs.PreferenceStorage

class LanguageViewModel @ViewModelInject constructor(
    private val preferenceStorage: PreferenceStorage,
) : ViewModel() {

    var currentLanguage : String
        get() = preferenceStorage.currentLanguage
        set(value) {
            preferenceStorage.currentLanguage = value
        }

}