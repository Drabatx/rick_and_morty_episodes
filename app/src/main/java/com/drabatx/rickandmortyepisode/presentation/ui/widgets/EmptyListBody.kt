package com.drabatx.rickandmortyepisode.presentation.ui.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.drabatx.rickandmortyepisode.R

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