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
) : BaseObject() {
    data class Builder(
        var title: String = "",
        var image: String = "",
        var id: Int = 0,
        var status: String = "",
        var species: String = "",
        var type: String = "",
        var gender: String = "",
        var origin: String = "",
        var location: String = ""
    ) {
        fun title(title: String) = apply { this.title = title }
        fun image(image: String) = apply { this.image = image }
        fun id(id: Int) = apply { this.id = id }
        fun status(status: String) = apply { this.status = status }
        fun species(species: String) = apply { this.species = species }
        fun type(type: String) = apply { this.type = type }
        fun gender(gender: String) = apply { this.gender = gender }
        fun origin(origin: String) = apply { this.origin = origin }
        fun location(location: String) = apply { this.location = location }

        fun build() =
            CharacterItem(title, image, id, status, species, type, gender, origin, location)
    }
}
