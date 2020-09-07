package hr.goodapp.warsapp.data.people

import hr.goodapp.warsapp.data.people.remote.People
import retrofit2.http.GET

interface PeopleDataSource {
  @GET("people")
  suspend fun getListOfPeople() : People
}