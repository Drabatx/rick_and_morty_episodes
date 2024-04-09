package com.drabatx.rickandmortyepisode.presentation.mapper

import com.drabatx.rickandmortyepisode.data.model.Episodes
import com.drabatx.rickandmortyepisode.presentation.model.EpisodeItem

object EpisodeResponseToEpisodeItemMapper {
    fun map(episode: Episodes) =
        EpisodeItem.Builder()
            .id(episode.id)
            .name(episode.name)
            .airDate(episode.air_date)
            .episode(episode.episode)
            .characters(episode.characters.map { it })
            .build()
}
