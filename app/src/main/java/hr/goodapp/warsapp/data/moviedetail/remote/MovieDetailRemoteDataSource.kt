package hr.goodapp.warsapp.data.moviedetail.remote

import hr.goodapp.warsapp.data.moviedetail.MovieDetailDataSource
import hr.goodapp.warsapp.data.moviedetail.remote.networkmodel.MovieDetail

class MovieDetailRemoteDataSource (private val movieDetailDao: MovieDetailDao) : MovieDetailDataSource {

    // TODO handle null pointer exception better
    override suspend fun getPeopleDetail(moviesUrl: List<String>?): List<MovieDetail> {

        val moviesDetail = mutableListOf<MovieDetail>()

        for (url in moviesUrl!!) {
            moviesDetail += movieDetailDao.getPeopleDetail(url)
        }

       return moviesDetail
    }
}