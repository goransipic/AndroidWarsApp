package hr.goodapp.warsapp.data.peopledetail

import hr.goodapp.warsapp.data.peopledetail.remote.networkmodel.MovieDetail

interface MovieDetailDataSource {
    suspend fun getPeopleDetail(id: List<String>?) : List<MovieDetail>
}