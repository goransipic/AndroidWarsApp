package hr.goodapp.warsapp.ui.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import hr.goodapp.warsapp.R
import hr.goodapp.warsapp.data.people.remote.networkmodel.People
import hr.goodapp.warsapp.data.people.remote.networkmodel.Result
import hr.goodapp.warsapp.databinding.MainFragmentBinding
import hr.goodapp.warsapp.ui.common.*
import hr.goodapp.warsapp.ui.common.adaptersdelegates.CardDelegateItem
import hr.goodapp.warsapp.ui.common.adaptersdelegates.SetItemHeightItem
import hr.goodapp.warsapp.ui.viewmodels.MainViewModel

class MainFragment : BaseFragment(R.layout.main_fragment) {

    private val viewModel by viewModels<MainViewModel>()
    private val viewBinding by viewBinding(MainFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val (adapter, clickEvent) = viewBinding.recyclerView.init()

        listenLiveData(viewModel.getPeople()) { people ->
            adapter.submitList(mapResult(people))
        }

        listenEventData(clickEvent){
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToDetailFragment(it as People,it.name!!))
        }
    }

    private fun mapResult(people: Result) =
        people.people!!.map { CardDelegateItem(it.name!!, it.height!!, payload = it) }
            .flatMap { listOf(it) + listOf(SetItemHeightItem()) }

}