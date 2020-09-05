package hr.goodapp.warsapp.ui.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import hr.goodapp.warsapp.R
import hr.goodapp.warsapp.databinding.MainFragmentBinding
import hr.goodapp.warsapp.ui.common.viewBinding
import hr.goodapp.warsapp.ui.viewmodels.MainViewModel

class MainFragment : Fragment(R.layout.main_fragment) {

    private lateinit var viewModel: MainViewModel
    private val viewBinding by viewBinding(MainFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }

}