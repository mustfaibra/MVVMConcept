package com.mustfaibra.mvvmconcept.screens.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.mustfaibra.mvvmconcept.R

@Composable
fun SplashScreen(
    splashViewModel: SplashViewModel = hiltViewModel(),
    onSplashFinished: () -> Unit,
) {
    splashViewModel.startSplash(
        onFinish = {
            onSplashFinished()
        }
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.primary),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = stringResource(id = R.string.app_name)
        )
    }
}