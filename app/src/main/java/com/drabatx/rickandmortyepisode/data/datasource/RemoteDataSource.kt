package com.drabatx.rickandmortyepisode.data.datasource

import com.drabatx.rickandmortyepisode.data.model.response.AllCharactersResponse
import com.drabatx.rickandmortyepisode.data.model.response.AllEpisodesResponse
import com.drabatx.rickandmortyepisode.data.network.CharactersApiService
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: CharactersApiService) {

    suspend fun getAllCharacters(): AllCharactersResponse {
        return apiService.getAllCharacters()
    }

    suspend fun getAllEpisodes(): AllEpisodesResponse {
        return apiService.getAllEpisodes()
    }

}
