package com.drabatx.rickandmortyepisode.data.network

import com.drabatx.rickandmortyepisode.data.model.response.AllCharactersResponse
import com.drabatx.rickandmortyepisode.data.model.response.AllEpisodesResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface CharactersApiService {

    @GET("character")
    suspend fun getAllCharacters(): AllCharactersResponse

    @GET("episode")
    suspend fun getAllEpisodes(): AllEpisodesResponse

}
