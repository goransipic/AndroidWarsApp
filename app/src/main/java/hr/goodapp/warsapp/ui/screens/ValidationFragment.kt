package hr.goodapp.warsapp.ui.screens

import android.os.Bundle
import android.os.Handler
import android.text.SpannableStringBuilder
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import hr.goodapp.warsapp.R
import hr.goodapp.warsapp.databinding.ValidationFragmentBinding
import hr.goodapp.warsapp.ui.common.BaseFragment
import hr.goodapp.warsapp.ui.common.toogleKeyboardVisibilty
import hr.goodapp.warsapp.ui.common.viewBinding
import hr.goodapp.warsapp.ui.viewmodels.ValidationViewModel
import java.lang.IllegalStateException


class ValidationFragment : BaseFragment(R.layout.validation_fragment) {

    companion object {
        const val RETURN_RESULT: String = "RETURN_RESULT"
        private const val HANDLER_ACTION = 1
    }

    private val viewModel  by viewModels<ValidationViewModel>()
    private val viewBinding by viewBinding(ValidationFragmentBinding::bind)
    private val handler = Handler{ navigateToMain()
                                   true }
    val function =
        {  }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewBinding.views.login.setOnClickListener {
            viewBinding.views.textInputLayout.error = null
            val text = viewBinding.views.textInputLayout.editText?.text.toString()
            viewBinding.views.textInputLayout.editText?.toogleKeyboardVisibilty(false)
            if (viewModel.checkInput(text)){
                if (!handler.hasMessages(HANDLER_ACTION)){
                    handler.sendEmptyMessageDelayed(HANDLER_ACTION, 500)
                }
            }else{
                viewBinding.views.textInputLayout.error = getString(R.string.wrong_input)
            }
        }

        viewBinding.views.rng.setOnClickListener {
            findNavController().navigate(
                ValidationFragmentDirections.actionValidationFragmentToRNGFragment(
                    viewBinding.views.textInputLayout.editText?.text.toString()
                )
            )
        }

        viewBinding.views.language.setOnClickListener {
            findNavController().navigate(R.id.action_validationFragment_to_languageFragment)
        }

        viewModel.viewStateData.observe(viewLifecycleOwner) {
            viewBinding.views.language.text = it.selectedLanguage
        }

        viewBinding.views.textInputLayout.editText?.doOnTextChanged { _, _, _, _ ->
            viewBinding.views.textInputLayout.isErrorEnabled = false
        }

        viewModel.refreshLanguage()

        listenForResult()

    }

    private fun navigateToMain() {
        val snackbar = Snackbar
            .make(requireView(), getString(R.string.sucess), Snackbar.LENGTH_SHORT)
        snackbar.show()

        findNavController().navigate(R.id.action_validationFragment_to_mainFragment)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.validation_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when(item.itemId){
        R.id.demo_login -> {navigateToMain()
                            true}
        else -> throw IllegalStateException("Not Allowed State")
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