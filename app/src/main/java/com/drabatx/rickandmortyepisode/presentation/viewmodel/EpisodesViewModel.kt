package com.drabatx.rickandmortyepisode.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.drabatx.rickandmortyepisode.data.model.response.AllEpisodesResponse
import com.drabatx.rickandmortyepisode.domain.usecase.GetAllEpisodesUseCase
import com.drabatx.rickandmortyepisode.presentation.model.EpisodeItem
import com.drabatx.rickandmortyepisode.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EpisodesViewModel @Inject constructor(private val getAllEpisodesUseCase: GetAllEpisodesUseCase) :
    ViewModel() {

    private val _episodesMutableStateFlow =
        MutableStateFlow<Result<AllEpisodesResponse>>(Result.Loading)
    val episodesStateFlow: StateFlow<Result<AllEpisodesResponse>>
        get() = _episodesMutableStateFlow

    var characters: List<EpisodeItem> by mutableStateOf(emptyList())

    fun getAllCharacters() {
        viewModelScope.launch {
            try {
                getAllEpisodesUseCase().collect { result ->
                    _episodesMutableStateFlow.value = result
                }
            } catch (e: Exception) {
                _episodesMutableStateFlow.value =
                    Result.Error(Throwable("Failed to load characters: ${e.message}"))
            }
        }
    }

    fun saveEpisodes(list: List<EpisodeItem>) {
        characters = list
    }


}
