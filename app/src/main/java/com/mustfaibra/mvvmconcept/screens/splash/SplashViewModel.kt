package com.mustfaibra.mvvmconcept.screens.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mustfaibra.mvvmconcept.repos.GameRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val gameRepo: GameRepo,
) : ViewModel() {

    fun startSplash(
        onFinish: () -> Unit,
    ){
        viewModelScope.launch {
            delay(3000)
            onFinish()
        }
    }
}