package hr.goodapp.warsapp.ui.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import hr.goodapp.warsapp.R
import hr.goodapp.warsapp.data.prefs.CROATIAN_LANGUAGE
import hr.goodapp.warsapp.data.prefs.ENGLISH_LANGUAGE
import hr.goodapp.warsapp.data.prefs.SharedPreferenceStorage
import hr.goodapp.warsapp.databinding.LanguageFragmentBinding
import hr.goodapp.warsapp.ui.common.BaseFragment
import hr.goodapp.warsapp.ui.common.LocalizationUtil
import hr.goodapp.warsapp.ui.common.viewBinding
import hr.goodapp.warsapp.ui.viewmodels.LanguageViewModel

class LanguageFragment : BaseFragment(R.layout.language_fragment) {

    private val viewModel by viewModels<LanguageViewModel>()
    private val viewBindings by viewBinding(LanguageFragmentBinding::bind)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        when(SharedPreferenceStorage(requireContext()).currentLanguage){
            ENGLISH_LANGUAGE ->  viewBindings.radioGroup.check(R.id.radioButtonEnglish)
            CROATIAN_LANGUAGE -> viewBindings.radioGroup.check(R.id.radioButtonCroatian)
        }

        viewBindings.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId){
                R.id.radioButtonEnglish -> {
                    setEnglishLanguage(ENGLISH_LANGUAGE)
                }
                R.id.radioButtonCroatian -> {
                    setCroatianLanguage(CROATIAN_LANGUAGE)
                }
            }
        }
    }

    private fun setCroatianLanguage(current: String) {
        if (current != SharedPreferenceStorage(requireContext()).currentLanguage) {
            LocalizationUtil.applyLanguageContext(
                requireContext(),
                CROATIAN_LANGUAGE)
            SharedPreferenceStorage(requireContext()).currentLanguage = CROATIAN_LANGUAGE
            requireActivity().recreate()
        }
    }

    private fun setEnglishLanguage(current: String) {
        if (current != SharedPreferenceStorage(requireContext()).currentLanguage) {
            LocalizationUtil.applyLanguageContext(
                requireContext(),
                ENGLISH_LANGUAGE)
            SharedPreferenceStorage(requireContext()).currentLanguage = ENGLISH_LANGUAGE
            requireActivity().recreate()
        }
    }


}