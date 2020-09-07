package hr.goodapp.warsapp.data.peopledetail.remote

import hr.goodapp.warsapp.data.peopledetail.remote.networkmodel.MovieDetail
import retrofit2.http.GET
import retrofit2.http.Url

interface MovieDetailDao {

    @GET
    suspend fun getPeopleDetail(@Url url: String) : MovieDetail
}
