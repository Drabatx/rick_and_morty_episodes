package com.drabatx.rickandmortyepisode.domain.usecase

import com.drabatx.rickandmortyepisode.data.model.response.AllEpisodesResponse
import com.drabatx.rickandmortyepisode.domain.repository.AllEpisodesRepository
import com.drabatx.rickandmortyepisode.utils.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllEpisodesUseCase @Inject constructor(private val repository: AllEpisodesRepository) {
    suspend operator fun invoke(): Flow<Result<AllEpisodesResponse>> {
        return repository.getAllEpisodes()
    }
}
