package hr.goodapp.warsapp.ui.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import hr.goodapp.warsapp.data.people.remote.networkmodel.People
import hr.goodapp.warsapp.data.peopledetail.MovieDetailDataSource
import hr.goodapp.warsapp.ui.common.launchLiveData

class DetailViewModel @ViewModelInject constructor(
    private val movieDetailDataSource: MovieDetailDataSource,
) : ViewModel() {

    fun getPeopleDetail(people: People) = launchLiveData { movieDetailDataSource.getPeopleDetail(people.films) }

}