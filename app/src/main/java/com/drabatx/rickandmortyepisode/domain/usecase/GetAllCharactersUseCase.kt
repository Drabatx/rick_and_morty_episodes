package com.drabatx.rickandmortyepisode.domain.usecase

import com.drabatx.rickandmortyepisode.data.model.response.AllCharactersResponse
import com.drabatx.rickandmortyepisode.domain.repository.AllCharactersRepository
import com.drabatx.rickandmortyepisode.utils.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllCharactersUseCase @Inject constructor(private val repository: AllCharactersRepository) {
    suspend operator fun invoke(): Flow<Result<AllCharactersResponse>> {
        return repository.getAllCharacters()
    }
}
