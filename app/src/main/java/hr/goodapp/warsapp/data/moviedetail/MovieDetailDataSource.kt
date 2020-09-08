package hr.goodapp.warsapp.data.moviedetail

import hr.goodapp.warsapp.data.moviedetail.remote.networkmodel.MovieDetail

interface MovieDetailDataSource {
    suspend fun getPeopleDetail(id: List<String>?) : List<MovieDetail>
}