package hr.goodapp.warsapp.ui.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import hr.goodapp.warsapp.R
import hr.goodapp.warsapp.databinding.RngfragmentBinding
import hr.goodapp.warsapp.ui.common.BaseFragment
import hr.goodapp.warsapp.ui.viewmodels.RNGViewModel
import hr.goodapp.warsapp.ui.common.viewBinding
import hr.goodapp.warsapp.ui.screens.ValidationFragment.Companion.RETURN_RESULT

class RNGFragment : BaseFragment(R.layout.rngfragment) {

    private val viewModel by viewModels<RNGViewModel>()
    private val viewBinding by viewBinding(RngfragmentBinding::bind)
    private val args by navArgs<RNGFragmentArgs>()
    private lateinit var savedStateHandle: SavedStateHandle


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        savedStateHandle = findNavController().previousBackStackEntry!!.savedStateHandle

        if (args.inputText != null && args.inputText!!.isNotEmpty()){
            viewBinding.views?.textView?.text = args.inputText
        }

        viewBinding.views?.buttonReturnResult?.setOnClickListener {
            savedStateHandle.set(RETURN_RESULT, viewModel.getLastNumber())
            findNavController().popBackStack()
        }

        viewBinding.views?.buttonGenerateNumber?.setOnClickListener {
            viewModel.generateNextNumber()

        }

        viewModel.viewState.observe(viewLifecycleOwner){
            viewBinding.views?.textView?.text = it
        }
    }

}