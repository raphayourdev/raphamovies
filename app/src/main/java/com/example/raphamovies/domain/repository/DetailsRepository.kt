package com.example.raphamovies.domain.repository

import com.example.raphamovies.network.ApiService
import com.example.raphamovies.presentation.base.BaseRepository
import com.example.raphamovies.utils.ResponseApi


class DetailsRepository : BaseRepository() {
    suspend fun getMovieById(id: Int): ResponseApi {
        return safeApiCall {
            ApiService.tmdbApi.getMovieById(id)
        }
    }
}