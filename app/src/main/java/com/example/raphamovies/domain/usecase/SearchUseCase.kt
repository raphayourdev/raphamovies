package com.example.raphamovies.domain.usecase

import com.example.raphamovies.data.model.MovieResult
import com.example.raphamovies.domain.repository.SearchRepository
import com.example.raphamovies.utils.ResponseApi
import com.example.raphamovies.utils.getFullImageUrl

class SearchUseCase
constructor(
    private val repository : SearchRepository
){
    suspend fun searchMovie(query: String): ResponseApi {
        return when (val responseApi = repository.searchMovie(query)) {
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
}