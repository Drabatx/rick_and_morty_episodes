package com.drabatx.rickandmortyepisode.data.model

data class Episodes(
    val id: Int,
    val name: String,
    val air_date: String,
    val episode: String,
    val characters: List<String>,
    val url: String,
    val created: String
)