package hr.goodapp.warsapp.ui.screens

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import hr.goodapp.warsapp.R
import hr.goodapp.warsapp.data.people.remote.networkdata.People
import hr.goodapp.warsapp.databinding.MainFragmentBinding
import hr.goodapp.warsapp.ui.common.*
import hr.goodapp.warsapp.ui.common.adaptersdelegates.CardDelegateItem
import hr.goodapp.warsapp.ui.common.adaptersdelegates.SetItemHeightItem
import hr.goodapp.warsapp.ui.common.adaptersdelegates.ThreeRowItem
import hr.goodapp.warsapp.ui.viewmodels.MainViewModel

class MainFragment : BaseFragment(R.layout.main_fragment) {

    private val viewModel by viewModels<MainViewModel>()
    private val viewBinding by viewBinding(MainFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val (adapter, _) = viewBinding.recyclerView.init()

        listenLiveData(viewModel.getPeople()) { people ->
            adapter.submitList(mapResult(people))
        }
    }

    private fun mapResult(people: People) =
        people.results!!.map { CardDelegateItem(it.name!!, it.height!!) }
            .flatMap { listOf(it) + listOf(SetItemHeightItem()) }

}