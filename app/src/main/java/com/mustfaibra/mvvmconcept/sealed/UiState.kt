package com.mustfaibra.mvvmconcept.sealed

sealed class UiState {
    object Success : UiState()
    object Error: UiState()
    object Loading: UiState()
    object Idle: UiState()
}
