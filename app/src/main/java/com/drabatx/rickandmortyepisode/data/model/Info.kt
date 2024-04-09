package com.drabatx.rickandmortyepisode.data.model

import com.drabatx.rickandmortyepisode.data.model.BaseObject

data class Info(
    val count: Int,
    val next: String,
    val pages: Int,
    val prev: String
): BaseObject()
