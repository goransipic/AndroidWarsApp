package hr.goodapp.warsapp.ui.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import hr.goodapp.warsapp.data.prefs.IPreferenceStorage

class LanguageViewModel @ViewModelInject constructor(
    private val IPreferenceStorage: IPreferenceStorage,
) : ViewModel() {

    var currentLanguage : String
        get() = IPreferenceStorage.currentLanguage
        set(value) {
            IPreferenceStorage.currentLanguage = value
        }

}