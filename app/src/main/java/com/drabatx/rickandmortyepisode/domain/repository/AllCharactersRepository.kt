package com.drabatx.rickandmortyepisode.domain.repository

import com.drabatx.rickandmortyepisode.data.model.response.AllCharactersResponse
import com.drabatx.rickandmortyepisode.utils.Result
import kotlinx.coroutines.flow.Flow

interface AllCharactersRepository {
    suspend fun getAllCharacters(): Flow<Result<AllCharactersResponse>>
}
