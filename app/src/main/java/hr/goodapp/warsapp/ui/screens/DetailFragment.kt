package hr.goodapp.warsapp.ui.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import hr.goodapp.warsapp.R
import hr.goodapp.warsapp.ui.common.BaseFragment
import hr.goodapp.warsapp.ui.common.listenLiveData
import hr.goodapp.warsapp.ui.viewmodels.DetailViewModel
import timber.log.Timber

class DetailFragment : BaseFragment(R.layout.detail_fragment) {

    private val viewModel by viewModels<DetailViewModel>()
    private val args by navArgs<DetailFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        listenLiveData(viewModel.getPeopleDetail(args.PersonDetail)){

        }
    }

}