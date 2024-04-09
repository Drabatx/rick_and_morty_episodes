package com.drabatx.rickandmortyepisode.presentation.ui.widgets

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import com.drabatx.rickandmortyepisode.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ParentTopBar(){
    TopAppBar(
        title = {
            Text(
                stringResource(R.string.app_name),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
    )
}