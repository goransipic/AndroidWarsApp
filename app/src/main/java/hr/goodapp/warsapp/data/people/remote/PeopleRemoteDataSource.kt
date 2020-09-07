package hr.goodapp.warsapp.data.people.remote

import hr.goodapp.warsapp.data.people.PeopleDataSource

class PeopleRemoteDataSource(private val peopleDataSource : PeopleDataSource) : PeopleDataSource {

    override suspend fun getListOfPeople(): People {
       return peopleDataSource.getListOfPeople()
    }

}