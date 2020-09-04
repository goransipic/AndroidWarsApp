package hr.goodapp.warsapp.ui.validation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import hr.goodapp.warsapp.R
import hr.goodapp.warsapp.databinding.ValidationFragmentBinding
import hr.goodapp.warsapp.ui.common.viewBinding


class ValidationFragment : Fragment(R.layout.validation_fragment) {

    //private lateinit var viewModel: ValidationViewModel
    private val viewBinding by viewBinding(ValidationFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val navController = findNavController()
        val appBarConfiguration = AppBarConfiguration(navController.graph)

        viewBinding.toolbar
            .setupWithNavController(navController, appBarConfiguration)
    }
}