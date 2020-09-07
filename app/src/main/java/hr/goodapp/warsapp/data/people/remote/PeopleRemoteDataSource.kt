package hr.goodapp.warsapp.data.people.remote

import hr.goodapp.warsapp.data.people.PeopleDataSource
import hr.goodapp.warsapp.data.people.remote.networkdata.People

class PeopleRemoteDataSource (private val peopleDao : PeopleDao) : PeopleDataSource {

    override suspend fun getListOfPeople(): People {
       return peopleDao.getListOfPeople()
    }

}