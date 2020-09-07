package hr.goodapp.warsapp.ui.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import hr.goodapp.warsapp.R
import hr.goodapp.warsapp.databinding.DetailFragmentBinding
import hr.goodapp.warsapp.ui.common.*
import hr.goodapp.warsapp.ui.common.adaptersdelegates.CardDelegateItem
import hr.goodapp.warsapp.ui.common.adaptersdelegates.SetItemHeightItem
import hr.goodapp.warsapp.ui.viewmodels.DetailViewModel
import timber.log.Timber

class DetailFragment : BaseFragment(R.layout.detail_fragment) {

    private val viewModel by viewModels<DetailViewModel>()
    private val viewBinding by viewBinding(DetailFragmentBinding::bind)
    private val args by navArgs<DetailFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val (adapter, _) = viewBinding.recyclerView.init()

        listenLiveData(viewModel.getPeopleDetail(args.PersonDetail)){ movies ->
            adapter.submitList(movies.map { CardDelegateItem(it.title!!, "Episode: ${it.episodeId!!.humanFormat}" ) }.flatMap { listOf(SetItemHeightItem()) + listOf(it) })
        }
    }

}