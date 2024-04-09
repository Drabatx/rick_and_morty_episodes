package com.drabatx.rickandmortyepisode.presentation.model

import com.drabatx.rickandmortyepisode.data.model.Character

data class EpisodeItem(
    val id: Int,
    val name: String,
    val air_date: String,
    val episode: String,
    val characters: List<String>
) {
    data class Builder(
        var id: Int = 0,
        var name: String = "",
        var air_date: String = "",
        var episode: String = "",
        var characters: List<String> = listOf()
    ) {
        fun id(id: Int) = apply { this.id = id }
        fun name(name: String) = apply { this.name = name }
        fun airDate(air_date: String) = apply { this.air_date = air_date }
        fun episode(episode: String) = apply { this.episode = episode }
        fun characters(characters: List<String>) = apply { this.characters = characters }
        fun build() = EpisodeItem(id, name, air_date, episode, characters)
    }
}