package com.drabatx.rickandmortyepisode.presentation.model

import com.drabatx.rickandmortyepisode.data.model.BaseObject


data class CharacterItem(
    val title: String,
    val image: String,
    val id: Int,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: String,
    val location: String,
) : BaseObject()
