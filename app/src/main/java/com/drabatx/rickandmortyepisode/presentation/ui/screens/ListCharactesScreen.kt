package com.drabatx.rickandmortyepisode.presentation.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.drabatx.rickandmortyepisode.R
import com.drabatx.rickandmortyepisode.data.model.response.AllCharactersResponse
import com.drabatx.rickandmortyepisode.presentation.adapter.ItemListView
import com.drabatx.rickandmortyepisode.presentation.dialogs.LoadingDialog
import com.drabatx.rickandmortyepisode.presentation.dialogs.MessageDialog
import com.drabatx.rickandmortyepisode.presentation.mapper.CharacterResponseToCharacterMapper
import com.drabatx.rickandmortyepisode.presentation.model.CharacterItem
import com.drabatx.rickandmortyepisode.presentation.ui.widgets.ChildTopBar
import com.drabatx.rickandmortyepisode.presentation.ui.widgets.ParentTopBar
import com.drabatx.rickandmortyepisode.presentation.viewmodel.CharactersListViewModel
import com.drabatx.rickandmortyepisode.utils.Result


@Composable
fun ListCharactersScreen(viewModel: CharactersListViewModel, navController: NavController) {
    val charactersState by viewModel.charactesStateFlow.collectAsState(initial = Result.Loading)

    Scaffold(
        topBar = {
            ChildTopBar(title = stringResource(id = R.string.characters_title), navController = navController)
        },
    ) { innerPadding ->
        when (charactersState) {
            is Result.Loading -> {
                LoadingDialog(isLoading = true)
            }

            is Result.Success -> {
                val charactersResponse =
                    (charactersState as Result.Success<AllCharactersResponse>).data
                val characters =
                    charactersResponse.results.map { CharacterResponseToCharacterMapper.map(it) }

                if (characters.isEmpty()) {
                    LaunchedEffect(Unit) {
                        viewModel.getAllCharacters()
                    }
                } else {
                    viewModel.saveCharacters(characters)
                    ListBody(innerPadding = innerPadding, characters)
                }
            }

            is Result.Error -> {
                val errorMessage = (charactersState as Result.Error).exception.message
                EmptyListBody(innerPadding = innerPadding)
                MessageDialog(
                    showDialog = true,
                    title = stringResource(R.string.text_error),
                    text = errorMessage.toString(),
                    icon = Icons.Default.Warning
                )

            }
        }
    }

    if (viewModel.characters.isEmpty()) {
        LaunchedEffect(Unit) {
            viewModel.getAllCharacters()
        }
    }
}


@Composable
fun ListBody(
    innerPadding: PaddingValues, characters: List<CharacterItem>
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(), contentPadding = innerPadding
    ) {
        items(characters) { character ->
            ItemListView(character)
        }
    }
}

@Composable
fun EmptyListBody(
    innerPadding: PaddingValues
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(innerPadding)
    ) {
        Text(
            text = stringResource(R.string.text_empty_list),
            modifier = Modifier.fillMaxWidth()
        )
    }
}