package hr.goodapp.warsapp.ui.screens

import android.os.Bundle
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.view.View
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.observe
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import hr.goodapp.warsapp.R
import hr.goodapp.warsapp.databinding.ValidationFragmentBinding
import hr.goodapp.warsapp.ui.common.BaseFragment
import hr.goodapp.warsapp.ui.common.Event
import hr.goodapp.warsapp.ui.common.EventObserver
import hr.goodapp.warsapp.ui.common.viewBinding
import hr.goodapp.warsapp.ui.viewmodels.ValidationViewModel


class ValidationFragment : BaseFragment(R.layout.validation_fragment) {

    companion object {
        const val RETURN_RESULT: String = "RETURN_RESULT"
    }

    private val viewModel  by viewModels<ValidationViewModel>()
    private val viewBinding by viewBinding(ValidationFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewBinding.views.login.setOnClickListener {
            findNavController().navigate(R.id.action_validationFragment_to_mainFragment)
        }

        viewBinding.views.rng.setOnClickListener {
            findNavController().navigate(ValidationFragmentDirections.actionValidationFragmentToRNGFragment(viewBinding.views.textInputLayout.editText?.text.toString()))
        }

        viewBinding.views.language.setOnClickListener {
            findNavController().navigate(R.id.action_validationFragment_to_languageFragment)
        }

        viewModel.viewStateData.observe(viewLifecycleOwner) {
            viewBinding.views.language.text = it.selectedLanguage
        }

        viewBinding.views.textInputLayout.editText?.doOnTextChanged { text, _, _, _, ->
           if (viewModel.checkInput(text.toString())){
               Toast.makeText(requireContext(), "OK", Toast.LENGTH_SHORT).show()
           }else{
               Toast.makeText(requireContext(), "NOK", Toast.LENGTH_SHORT).show()
           }
        }

        viewModel.refreshLanguage()

        listenForResult()

    }

    private fun listenForResult() {
        val navController = findNavController()

        val currentBackStackEntry = navController.currentBackStackEntry!!
        val savedStateHandle = currentBackStackEntry.savedStateHandle

        savedStateHandle.getLiveData<String>(RETURN_RESULT)
            .observe(viewLifecycleOwner, Observer { success ->
                viewBinding.views.textInputLayout.editText?.text = SpannableStringBuilder(success)
                //Toast.makeText(requireContext(), success, Toast.LENGTH_SHORT).show()
                savedStateHandle.remove<String>(RETURN_RESULT)
            })
    }
}