package com.mustfaibra.mvvmconcept.screens.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mustfaibra.mvvmconcept.data.models.Game
import com.mustfaibra.mvvmconcept.repos.GameRepo
import com.mustfaibra.mvvmconcept.sealed.DataResponse
import com.mustfaibra.mvvmconcept.sealed.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val gameRepo: GameRepo,
) : ViewModel() {
    val homeUiState = mutableStateOf<UiState>(UiState.Loading)
    val games = mutableListOf<Game>()
    val message = mutableStateOf("")

    fun getGames() {
        homeUiState.value = UiState.Loading
        viewModelScope.launch {
            gameRepo.getGames().let {
                when (it) {
                    is DataResponse.Success -> {
                        homeUiState.value = UiState.Success
                        it.data?.let { myGames ->
                            games.addAll(myGames)
                        }
                    }
                    is DataResponse.Error -> {
                        homeUiState.value = UiState.Error
                        message.value = it.message ?: "Unknown error happened!"
                    }
                }
            }

        }
    }
}