package com.example.raphamovies.domain.usecase

import com.example.raphamovies.data.model.Movie
import com.example.raphamovies.domain.repository.DetailsRepository
import com.example.raphamovies.utils.ResponseApi
import com.example.raphamovies.utils.getFullImageUrl


class DetailsUseCase
constructor(
    private val repository : DetailsRepository
){
    suspend fun getMovieById(movieId: Int): ResponseApi {
        return when(val responseApi = repository.getMovieById(movieId)) {
            is ResponseApi.Success -> {
                val movie = responseApi.data as? Movie
                movie?.backdrop_path = movie?.backdrop_path?.getFullImageUrl().toString()
                ResponseApi.Success(movie)
            }
            is ResponseApi.Error -> {
                responseApi
            }
        }
    }

}
