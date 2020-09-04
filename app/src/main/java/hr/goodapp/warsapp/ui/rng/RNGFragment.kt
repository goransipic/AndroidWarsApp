package hr.goodapp.warsapp.ui.rng

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import hr.goodapp.warsapp.R

class RNGFragment : Fragment() {

    companion object {
        fun newInstance() = RNGFragment()
    }

    private lateinit var viewModel: RNGViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.rngfragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(RNGViewModel::class.java)
        // TODO: Use the ViewModel
    }

}