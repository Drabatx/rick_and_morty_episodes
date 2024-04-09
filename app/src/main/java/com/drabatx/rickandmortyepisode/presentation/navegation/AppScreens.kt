package com.drabatx.rickandmortyepisode.presentation.navegation

import androidx.annotation.StringRes
import com.drabatx.rickandmortyepisode.R

sealed class AppScreens(val route: String, @StringRes val resourceId: Int) {
    data object MenuScreen: AppScreens("menu_screen", R.string.menu_screen)
    data object ListScreen: AppScreens("list_screen", R.string.characters_screen)
    data object EpisodeScreen: AppScreens("episodes_screen", R.string.episodes_screen)
}