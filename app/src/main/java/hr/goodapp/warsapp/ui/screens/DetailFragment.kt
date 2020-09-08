package hr.goodapp.warsapp.ui.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import hr.goodapp.warsapp.R
import hr.goodapp.warsapp.data.moviedetail.remote.networkmodel.MovieDetail
import hr.goodapp.warsapp.databinding.DetailFragmentBinding
import hr.goodapp.warsapp.ui.common.*
import hr.goodapp.warsapp.ui.common.adaptersdelegates.DisplayableItem
import hr.goodapp.warsapp.ui.common.adaptersdelegates.SetItemHeightItem
import hr.goodapp.warsapp.ui.common.adaptersdelegates.SingleRowItem
import hr.goodapp.warsapp.ui.common.adaptersdelegates.TwoRowItem
import hr.goodapp.warsapp.ui.viewmodels.DetailViewModel

class DetailFragment : BaseFragment(R.layout.detail_fragment) {

    private val viewModel by viewModels<DetailViewModel>()
    private val viewBinding by viewBinding(DetailFragmentBinding::bind)
    private val args by navArgs<DetailFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val (adapter, _) = viewBinding.recyclerView.init()
        val personDetail = mutableListOf<DisplayableItem>()
        personDetail += SingleRowItem(getString(R.string.actor_details))
        personDetail += TwoRowItem(getString(R.string.name),args.PersonDetail.name!!)
        personDetail += TwoRowItem(getString(R.string.birth_year),args.PersonDetail.birthYear!!)
        personDetail += TwoRowItem(getString(R.string.gender),args.PersonDetail.gender!!)
        personDetail += TwoRowItem(getString(R.string.skin_color),args.PersonDetail.skinColor!!)


        listenLiveData(viewModel.getPeopleDetail(args.PersonDetail)){ movies ->
            adapter.submitList(handleResult(movies, personDetail))
        }
    }

    private fun handleResult(
        movies: List<MovieDetail>,
        personDetail: MutableList<DisplayableItem>
    ) =
       personDetail.flatMap { listOf(it) + listOf(SetItemHeightItem())  } + listOf(SingleRowItem(getString(R.string.movies)),SetItemHeightItem()) + movies.map {
            TwoRowItem(
                it.title!!,
                "Episode: ${it.episodeId!!.humanFormat}"
            )
        }.flatMap { listOf(it) + listOf(SetItemHeightItem()) }

}