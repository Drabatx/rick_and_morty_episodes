package com.drabatx.rickandmortyepisode.presentation.adapter

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.drabatx.rickandmortyepisode.presentation.model.EpisodeItem

@Composable
fun ItemEpisodeView(episode: EpisodeItem) {
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
                val (nameText, episodeText, airdateText) = createRefs()

                Text(
                    text = episode.episode,
                    style = MaterialTheme.typography.bodyLarge.copy(fontSize = 14.sp),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .constrainAs(episodeText) {
                            top.linkTo(parent.top)
                            end.linkTo(parent.end)
                        }
                        .padding(horizontal = 16.dp, vertical = 16.dp)
                )

                Text(
                    text = episode.air_date,
                    style = MaterialTheme.typography.bodyLarge.copy(fontSize = 16.sp),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .constrainAs(airdateText) {
                            top.linkTo(nameText.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            width = Dimension.fillToConstraints
                        }
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                )

                Text(
                    text = episode.name,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier
                        .constrainAs(nameText) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            end.linkTo(episodeText.start)
                            width = Dimension.fillToConstraints
                        }
                        .padding(horizontal = 16.dp, vertical = 16.dp),
                    overflow = TextOverflow.Ellipsis // Trunca el texto si es muy largo
                )
            }
        }
    }
}

