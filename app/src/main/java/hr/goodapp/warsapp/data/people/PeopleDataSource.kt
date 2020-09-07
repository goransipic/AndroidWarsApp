package hr.goodapp.warsapp.data.people

import hr.goodapp.warsapp.data.people.remote.networkmodel.Result

interface PeopleDataSource {
  suspend fun getListOfPeople() : Result // TODO Need Model Classes for separation between layers UI for example UI does not need to know any implementation detail from Retrofit annotation in classes
}