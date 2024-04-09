package com.drabatx.rickandmortyepisode.presentation.adapter

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import com.drabatx.rickandmortyepisode.R
import com.drabatx.rickandmortyepisode.presentation.model.CharacterItem

@Composable
fun ItemCharacterView(character: CharacterItem) {
    var expanded by rememberSaveable { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            shape = MaterialTheme.shapes.medium
        ) {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(bottom = 16.dp)
            ) {
                val (image, title, button, details) = createRefs()
                AsyncImage(
                    model = character.image,
                    contentDescription = character.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .constrainAs(image) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                        .aspectRatio(2f / 2f)
                )

                Text(
                    text = character.title,
                    style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .constrainAs(title) {
                            top.linkTo(image.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                )
                Button(
                    onClick = { expanded = !expanded },
                    modifier = Modifier.constrainAs(button) {
                        top.linkTo(title.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }) {
                    Text(
                        if (!expanded) stringResource(R.string.action_show_details) else stringResource(
                            R.string.action_hide_details
                        )
                    )
                }
                if (expanded) {
                    // Mostrar detalles adicionales cuando se expanda
                    DetailCharacterSection(character = character, modifier = Modifier.constrainAs(details) {
                        top.linkTo(button.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    })
                }
            }
        }
    }
}

@Composable
fun DetailCharacterSection(character: CharacterItem, modifier: Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        if (!character.status.isNullOrEmpty()) Text(text = stringResource(
            R.string.text_status,
            character.status
        ))
        if (!character.species.isNullOrEmpty()) Text(text = stringResource(
            R.string.text_species,
            character.species
        ))
        if (!character.type.isNullOrEmpty()) Text(text = stringResource(
            R.string.text_type,
            character.type
        ))
        if (!character.gender.isNullOrEmpty()) Text(text = stringResource(
            R.string.text_gender,
            character.gender
        ))
        if (!character.origin.isNullOrEmpty()) Text(text = stringResource(
            R.string.text_origin,
            character.origin
        ))
        if (!character.location.isNullOrEmpty()) Text(text = stringResource(
            R.string.text_location,
            character.location
        ))
    }
}
