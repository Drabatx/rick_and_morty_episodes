package com.drabatx.rickandmortyepisode.data.model.response

import com.drabatx.rickandmortyepisode.data.model.Character
import com.drabatx.rickandmortyepisode.data.model.Info

data class AllCharactersResponse(
    val info: Info,
    val results: List<Character>
)
