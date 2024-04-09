package com.drabatx.rickandmortyepisode.presentation.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.drabatx.rickandmortyepisode.R
import com.drabatx.rickandmortyepisode.presentation.navegation.AppScreens
import com.drabatx.rickandmortyepisode.presentation.ui.widgets.ParentTopBar

@Composable
fun MenuScreen(navController: NavController) {
    Scaffold(
        topBar = {
            ParentTopBar()
        }
    ) { innerPadding ->
        MenuButtons(innerPadding, navController)
    }
}

@Composable
fun MenuButtons(innerPadding: PaddingValues, navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(onClick = {
                navController.navigate(AppScreens.ListScreen.route)
            }) {
                Text(text = stringResource(id = R.string.characters_menu))
            }

            Button(onClick = {
                navController.navigate(AppScreens.EpisodeScreen.route)
            }) {
                Text(text = stringResource(id = R.string.episodes_menu))
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun prevideComponent(navController: NavController){
//    Scaffold(
//        topBar = {
//            ParentTopBar()
//        }
//    ) { innerPadding ->
//        MenuButtons(innerPadding, navController)
//    }
//}