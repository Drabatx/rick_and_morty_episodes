package com.drabatx.rickandmortyepisode.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.drabatx.rickandmortyepisode.data.model.response.AllCharactersResponse
import com.drabatx.rickandmortyepisode.domain.usecase.GetAllCharactersUseCase
import com.drabatx.rickandmortyepisode.presentation.model.CharacterItem
import com.drabatx.rickandmortyepisode.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(private val getAllCharactersUseCase: GetAllCharactersUseCase) : ViewModel() {

    private val _characterMutableStateFlow =
        MutableStateFlow<Result<AllCharactersResponse>>(Result.Loading)
    val charactesStateFlow: StateFlow<Result<AllCharactersResponse>>
        get() = _characterMutableStateFlow

    var characters: List<CharacterItem> by mutableStateOf(emptyList())

    fun getAllCharacters() {
        viewModelScope.launch {
            try {
                getAllCharactersUseCase().collect { result->
                    _characterMutableStateFlow.value = result
                }
            } catch (e: Exception) {
                _characterMutableStateFlow.value =
                    Result.Error(Throwable("Failed to load characters: ${e.message}"))
            }
        }
    }

    fun saveCharacters(list: List<CharacterItem>){
        characters = list
    }


}
