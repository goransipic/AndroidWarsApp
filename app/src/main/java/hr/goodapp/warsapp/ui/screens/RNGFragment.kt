package hr.goodapp.warsapp.ui.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import hr.goodapp.warsapp.R
import hr.goodapp.warsapp.databinding.RngfragmentBinding
import hr.goodapp.warsapp.ui.common.BaseFragment
import hr.goodapp.warsapp.ui.common.Event
import hr.goodapp.warsapp.ui.viewmodels.RNGViewModel
import hr.goodapp.warsapp.ui.common.viewBinding
import hr.goodapp.warsapp.ui.screens.ValidationFragment.Companion.RETURN_RESULT

class RNGFragment : BaseFragment(R.layout.rngfragment) {

    private lateinit var viewModel: RNGViewModel
    private val viewBinding by viewBinding(RngfragmentBinding::bind)
    private val args by navArgs<RNGFragmentArgs>()
    private lateinit var savedStateHandle: SavedStateHandle


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        savedStateHandle = findNavController().previousBackStackEntry!!.savedStateHandle

        viewBinding.textView2.text = args.inputText.toString()

        viewBinding.button.setOnClickListener {
            savedStateHandle.set(RETURN_RESULT, "Return from RNG")
            findNavController().popBackStack()
        }
    }

}