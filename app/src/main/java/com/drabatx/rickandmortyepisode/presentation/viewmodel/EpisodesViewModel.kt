package com.drabatx.rickandmortyepisode.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.drabatx.rickandmortyepisode.data.model.response.AllEpisodesResponse
import com.drabatx.rickandmortyepisode.domain.usecase.GetAllEpisodesUseCase
import com.drabatx.rickandmortyepisode.presentation.model.EpisodeItem
import com.drabatx.rickandmortyepisode.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EpisodesViewModel @Inject constructor(private val getAllEpisodesUseCase: GetAllEpisodesUseCase) :
    ViewModel() {

    private val _episodesMutableStateFlow =
        MutableStateFlow<Result<AllEpisodesResponse>>(Result.Loading)
    val episodesStateFlow: StateFlow<Result<AllEpisodesResponse>>
        get() = _episodesMutableStateFlow

    var episodes: List<EpisodeItem> by mutableStateOf(emptyList())

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _episodesFilter = MutableStateFlow(episodes)
    val episodesFilter = searchText
        .combine(_episodesFilter) { text, list ->
            if (text.isBlank()) {
                list
            }
            list.filter { episodeItem ->
                episodeItem.name.uppercase().contains(text.trim().uppercase())
                    .or(episodeItem.episode.uppercase().contains(text.trim().uppercase()))
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = _episodesFilter.value
        )

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

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }

    fun onToogleSearch() {
        _isSearching.value = !_isSearching.value
        if (!_isSearching.value) {
            onSearchTextChange("")
        }
    }

    fun saveEpisodes(list: List<EpisodeItem>) {
        episodes = list
        _episodesFilter.value = episodes
    }

    companion object {
        private const val TAG = "EpisodesViewModel"
    }
}
