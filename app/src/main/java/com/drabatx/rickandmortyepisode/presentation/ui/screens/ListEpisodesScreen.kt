package com.drabatx.rickandmortyepisode.presentation.ui.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.drabatx.rickandmortyepisode.R
import com.drabatx.rickandmortyepisode.data.model.response.AllEpisodesResponse
import com.drabatx.rickandmortyepisode.presentation.adapter.ItemCharacterView
import com.drabatx.rickandmortyepisode.presentation.adapter.ItemEpisodeView
import com.drabatx.rickandmortyepisode.presentation.dialogs.LoadingDialog
import com.drabatx.rickandmortyepisode.presentation.dialogs.MessageDialog
import com.drabatx.rickandmortyepisode.presentation.mapper.EpisodeResponseToEpisodeItemMapper
import com.drabatx.rickandmortyepisode.presentation.model.EpisodeItem
import com.drabatx.rickandmortyepisode.presentation.ui.widgets.ChildTopBar
import com.drabatx.rickandmortyepisode.presentation.ui.widgets.EmptyListBody
import com.drabatx.rickandmortyepisode.presentation.viewmodel.EpisodesViewModel
import com.drabatx.rickandmortyepisode.utils.Result

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListEpisodesScreen(viewModel: EpisodesViewModel, navController: NavController) {
    val episodesState by viewModel.episodesStateFlow.collectAsState(initial = Result.Loading)
    Scaffold(
        topBar = {
            SearchBar(
                query = "searchText"
            )
        },
    ){ innerPadding ->
        when (episodesState) {
            is Result.Loading -> {
                LoadingDialog(isLoading = true)
            }

            is Result.Success -> {
                val episodesResponse =
                    (episodesState as Result.Success<AllEpisodesResponse>).data
                val episodes =
                    episodesResponse.results.map { EpisodeResponseToEpisodeItemMapper.map(it) }

                if (episodes.isEmpty()) {
                    LaunchedEffect(Unit) {
                        viewModel.getAllCharacters()
                    }
                } else {
                    viewModel.saveEpisodes(episodes)
                    ListEpisodesBody(innerPadding = innerPadding, episodes)
                }
            }

            is Result.Error -> {
                val errorMessage = (episodesState as Result.Error).exception.message
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
fun ListEpisodesBody(
    innerPadding: PaddingValues, episodes: List<EpisodeItem>
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(), contentPadding = innerPadding
    ) {
        items(episodes) { episode ->
            ItemEpisodeView(episode = episode)
        }
    }
}