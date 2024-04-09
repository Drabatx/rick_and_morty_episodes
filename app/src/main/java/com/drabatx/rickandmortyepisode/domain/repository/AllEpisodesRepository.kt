package com.drabatx.rickandmortyepisode.domain.repository

import com.drabatx.rickandmortyepisode.data.model.response.AllEpisodesResponse
import com.drabatx.rickandmortyepisode.utils.Result
import kotlinx.coroutines.flow.Flow

interface AllEpisodesRepository {
    suspend fun getAllEpisodes(): Flow<Result<AllEpisodesResponse>>
}
