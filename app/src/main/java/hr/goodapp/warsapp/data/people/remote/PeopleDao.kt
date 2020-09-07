package hr.goodapp.warsapp.data.people.remote

import hr.goodapp.warsapp.data.people.remote.networkdata.People
import retrofit2.http.GET

interface PeopleDao {
    @GET("people")
    suspend fun getListOfPeople() : People // TODO Need Model Classes for separation between layers UI for example UI does not need to know any implementation detail from Retrofit annotation in classes
}