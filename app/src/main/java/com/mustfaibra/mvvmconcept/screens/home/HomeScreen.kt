package com.mustfaibra.mvvmconcept.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.mustfaibra.mvvmconcept.sealed.UiState

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel(),
) {
    LaunchedEffect(key1 = Unit){
        homeViewModel.getGames()
    }

    val uiState by remember { homeViewModel.homeUiState }
    val games = homeViewModel.games
    val message by remember { homeViewModel.message }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        when(uiState){
            is UiState.Idle -> {

            }
            is UiState.Loading -> {
                item {
                    CircularProgressIndicator(
                        color = MaterialTheme.colors.primary,
                    )
                }
            }
            is UiState.Success -> {
                items(games){game->
                    GameItemLayout(
                        gameName = game.title,
                        gameImage = game.thumbnail,
                        gameDescription = game.description,
                        gameGenre = game.genre,
                    )
                }
            }
            is UiState.Error -> {
                item {
                    Text(
                        text = message
                    )
                }
            }
        }
    }
}

@Composable
fun GameItemLayout(
    gameName:String,
    gameImage: String,
    gameDescription: String?,
    gameGenre: String,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ){
        AsyncImage(
            model = gameImage,
            contentDescription = null,
            modifier = Modifier
                .size(60.dp)
                .clip(MaterialTheme.shapes.medium),
            contentScale = ContentScale.Crop,
        )
        Column(
            modifier = Modifier
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = gameGenre,
                style = MaterialTheme.typography.caption,
            )
            Text(
                text = gameName,
                style = MaterialTheme.typography.body1,
            )
            Text(
                text = gameDescription ?: "No Description",
                maxLines = 3,
                style = MaterialTheme.typography.body2,
            )

        }
    }
}