package com.drabatx.rickandmortyepisode.presentation.mapper

import com.drabatx.rickandmortyepisode.data.model.Character
import com.drabatx.rickandmortyepisode.presentation.model.CharacterItem

object CharacterResponseToCharacterMapper {

    fun map(character: Character): CharacterItem {
        return CharacterItem(
            character.name,
            character.image,
            character.id,
            character.status,
            character.species,
            character.type,
            character.gender,
            character.origin.name,
            character.location.name
        )
    }
}
