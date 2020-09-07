package hr.goodapp.warsapp.ui.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import hr.goodapp.warsapp.data.people.PeopleDataSource
import hr.goodapp.warsapp.data.people.remote.networkdata.People

class MainViewModel @ViewModelInject constructor(
    private val peopleDataSource: PeopleDataSource,
) : ViewModel()  {
    fun getPeople() = liveData { emit(peopleDataSource.getListOfPeople()) }
}