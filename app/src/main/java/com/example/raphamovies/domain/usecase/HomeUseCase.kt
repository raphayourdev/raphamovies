package com.example.raphamovies.domain.usecase

import bcom.example.raphamovies.utils.Constants.FIRST_PAGE
import com.example.raphamovies.data.model.Movie
import com.example.raphamovies.data.model.MovieResult
import com.example.raphamovies.domain.repository.HomeRepository
import com.example.raphamovies.utils.ResponseApi
import com.example.raphamovies.utils.dateFormat
import com.example.raphamovies.utils.getFullImageUrl


class  HomeUseCase
constructor(
    private val repository : HomeRepository
) {
    suspend fun getPopularMovies(): ResponseApi {
        return when (val responseApi = repository.getPopularMovies(FIRST_PAGE)) {
            is ResponseApi.Success -> {
                val data = responseApi.data as? MovieResult
                val result = data?.results?.map {
                    it.backdrop_path = it.backdrop_path.getFullImageUrl()
                    it.poster_path = it.poster_path.getFullImageUrl()
                    it
                }
                ResponseApi.Success(result)
            }
            is ResponseApi.Error -> {
                responseApi
            }
        }
    }

    fun setupMoviesList(list: MovieResult?): List<Movie> {
        val movies = list?.results
        movies?.forEach { movie ->
            movie.poster_path = movie.poster_path?.getFullImageUrl()
            movie.backdrop_path = movie.backdrop_path?.getFullImageUrl()
            movie.release_date = movie.release_date?.dateFormat()
        }
        return movies ?: listOf()
    }



}