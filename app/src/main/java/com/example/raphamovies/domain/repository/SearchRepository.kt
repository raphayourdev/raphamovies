package com.example.raphamovies.domain.repository

import com.example.raphamovies.network.ApiService
import com.example.raphamovies.presentation.base.BaseRepository
import com.example.raphamovies.utils.ResponseApi


class SearchRepository : BaseRepository() {
    suspend fun searchMovie(query: String): ResponseApi {
        return safeApiCall {
            ApiService.tmdbApi.searchMovie(query)
        }
    }
}