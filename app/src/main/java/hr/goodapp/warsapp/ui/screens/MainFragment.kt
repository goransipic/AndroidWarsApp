package hr.goodapp.warsapp.ui.screens

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import hr.goodapp.warsapp.R
import hr.goodapp.warsapp.databinding.MainFragmentBinding
import hr.goodapp.warsapp.ui.common.BaseFragment
import hr.goodapp.warsapp.ui.common.viewBinding
import hr.goodapp.warsapp.ui.viewmodels.MainViewModel

class MainFragment : BaseFragment(R.layout.main_fragment) {

    private val viewModel by viewModels<MainViewModel>()
    private val viewBinding by viewBinding(MainFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewBinding.recyclerView

        viewModel.getPeople().observe(viewLifecycleOwner){
            Log.d("MainFragment", it.toString())
        }
    }

}