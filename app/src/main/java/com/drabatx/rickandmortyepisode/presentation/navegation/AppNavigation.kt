package com.drabatx.rickandmortyepisode.presentation.navegation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.drabatx.rickandmortyepisode.presentation.navegation.AppScreens
import com.drabatx.rickandmortyepisode.presentation.ui.screens.ListCharactersScreen
import com.drabatx.rickandmortyepisode.presentation.ui.screens.MenuScreen
import com.drabatx.rickandmortyepisode.presentation.viewmodel.CharactersListViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val charactersListViewModel: CharactersListViewModel = viewModel()

    NavHost(navController = navController, startDestination = AppScreens.MenuScreen.route) {
        composable(route = AppScreens.MenuScreen.route){
            MenuScreen(navController = navController)
        }
        composable(route = AppScreens.ListScreen.route) {
            ListCharactersScreen(charactersListViewModel, navController)
        }
    }
}