package hr.goodapp.warsapp.data.people.remote

import hr.goodapp.warsapp.data.people.PeopleDataSource
import hr.goodapp.warsapp.data.people.remote.networkmodel.Result

class PeopleRemoteDataSource (private val peopleDao : PeopleDao) : PeopleDataSource {

    override suspend fun getListOfPeople(): Result {
       return peopleDao.getListOfPeople()
    }

}