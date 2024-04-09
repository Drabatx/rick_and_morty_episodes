package com.drabatx.rickandmortyepisode.domain.repository.impl

import com.drabatx.rickandmortyepisode.data.datasource.RemoteDataSource
import com.drabatx.rickandmortyepisode.data.model.response.AllEpisodesResponse
import com.drabatx.rickandmortyepisode.domain.repository.AllEpisodesRepository
import com.drabatx.rickandmortyepisode.utils.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AllEpisodesRepositoryImpl @Inject constructor(private val remoteDataSource: RemoteDataSource) :
    AllEpisodesRepository {
    override suspend fun getAllEpisodes(): Flow<Result<AllEpisodesResponse>> = flow {
        try {
            val response = remoteDataSource.getAllEpisodes()
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }
}
