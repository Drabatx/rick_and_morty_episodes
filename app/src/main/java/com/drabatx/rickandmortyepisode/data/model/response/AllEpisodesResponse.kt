package com.drabatx.rickandmortyepisode.data.model.response

import com.drabatx.rickandmortyepisode.data.model.Episodes
import com.drabatx.rickandmortyepisode.data.model.Info

data class AllEpisodesResponse(
    val info: Info,
    val results: List<Episodes>
)
