package com.drabatx.rickandmortyepisode.domain.repository.impl

import com.drabatx.rickandmortyepisode.data.datasource.RemoteDataSource
import com.drabatx.rickandmortyepisode.data.model.response.AllCharactersResponse
import com.drabatx.rickandmortyepisode.domain.repository.AllCharactersRepository
import com.drabatx.rickandmortyepisode.utils.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AllCharacterRepositoryImpl @Inject constructor(private val remoteDataSource: RemoteDataSource) :
    AllCharactersRepository {
    override suspend fun getAllCharacters(): Flow<Result<AllCharactersResponse>> = flow {
        try {
            val response = remoteDataSource.getAllCharacters()
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }
}
