package com.drabatx.rickandmortyepisode.presentation.mapper

import com.drabatx.rickandmortyepisode.data.model.Character
import com.drabatx.rickandmortyepisode.presentation.model.CharacterItem

object CharacterResponseToCharacterMapper {
    fun map(character: Character) =
        CharacterItem.Builder()
            .title(character.name)
            .image(character.image)
            .id(character.id)
            .status(character.status)
            .species(character.species)
            .type(character.type)
            .gender(character.gender)
            .origin(character.origin.name)
            .location(character.location.name)
            .build()
}
