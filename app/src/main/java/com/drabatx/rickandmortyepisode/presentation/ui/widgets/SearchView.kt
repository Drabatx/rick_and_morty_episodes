package com.drabatx.rickandmortyepisode.presentation.ui.widgets

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.drabatx.rickandmortyepisode.R
import com.drabatx.rickandmortyepisode.presentation.viewmodel.EpisodesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchView(
    query: String,
    onQueryChange: (String) -> Unit,
    viewModel: EpisodesViewModel
) {

    TopAppBar(
        title = {
            OutlinedTextField(
                value = query,
                onValueChange = onQueryChange,
                placeholder = { Text(stringResource(id = R.string.action_hint_search)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 8.dp),
                singleLine = true,
                leadingIcon = {
                    IconButton(onClick = { viewModel.onToogleSearch() }) {
                        Icon(Icons.Filled.Close, contentDescription = "Back")
                    }
                }
            )
        }
    )
}
