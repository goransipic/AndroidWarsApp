package hr.goodapp.warsapp.ui.screens

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import hr.goodapp.warsapp.ui.common.viewBinding
import hr.goodapp.warsapp.R
import hr.goodapp.warsapp.databinding.LanguageFragmentBinding
import hr.goodapp.warsapp.ui.common.BaseFragment
import hr.goodapp.warsapp.ui.viewmodels.LanguageViewModel

class LanguageFragment : BaseFragment(R.layout.language_fragment) {

    private val viewModel by viewModels<LanguageViewModel>()
    private val viewBindings by viewBinding(LanguageFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewBindings.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId){
                R.id.radioButtonEnglish -> { Toast.makeText(requireContext(),"English",Toast.LENGTH_LONG).show()}
                R.id.radioButtonCroatian -> {Toast.makeText(requireContext(),"Croatian",Toast.LENGTH_LONG).show()}
            }
        }
    }

}