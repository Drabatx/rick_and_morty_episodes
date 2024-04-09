package com.drabatx.rickandmortyepisode.data.model

import java.text.SimpleDateFormat
import java.util.Locale

data class Episodes(
    val id: Int,
    val name: String,
    val air_date: String,
    val episode: String,
    val characters: List<Character>,
    val url: String,
    val created: String
) {
    fun String.airDateToCustomDate(): String =
        SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH).run {
            SimpleDateFormat("d MMMM yyyy", Locale.ENGLISH).format(parse(this@airDateToCustomDate))
        }


    fun String.createdToCustomDate(): String =
        SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH).run {
            SimpleDateFormat("d MMMM yyyy", Locale.ENGLISH).format(parse(this@createdToCustomDate))
        }
}