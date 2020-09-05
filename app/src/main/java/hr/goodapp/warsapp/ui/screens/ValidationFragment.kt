package hr.goodapp.warsapp.ui.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import hr.goodapp.warsapp.R
import hr.goodapp.warsapp.databinding.ValidationFragmentBinding
import hr.goodapp.warsapp.ui.common.BaseFragment
import hr.goodapp.warsapp.ui.common.viewBinding
import hr.goodapp.warsapp.ui.viewmodels.ValidationViewModel


class ValidationFragment : BaseFragment(R.layout.validation_fragment) {

    private val viewModel  by viewModels<ValidationViewModel>()
    private val viewBinding by viewBinding(ValidationFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewBinding.login.setOnClickListener {
            findNavController().navigate(R.id.action_validationFragment_to_mainFragment)
        }

        viewBinding.rng.setOnClickListener {
            findNavController().navigate(R.id.action_validationFragment_to_RNGFragment)
        }

        viewBinding.language.setOnClickListener {
            findNavController().navigate(R.id.action_validationFragment_to_languageFragment)
        }

        viewModel.viewStateData.observe(viewLifecycleOwner) {
            viewBinding.language.text = it.selectedLanguage
        }

        viewModel.refreshLanguage()
    }
}