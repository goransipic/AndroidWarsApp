package hr.goodapp.warsapp.data.people

import hr.goodapp.warsapp.data.people.remote.networkdata.People

interface PeopleDataSource {
  suspend fun getListOfPeople() : People // TODO Need Model Classes for separation between layers UI for example UI does not need to know any implementation detail from Retrofit annotation in classes
}