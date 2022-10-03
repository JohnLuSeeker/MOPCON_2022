package tw.kotlin.mopcon2022.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import tw.kotlin.core.network.RestApiImpl
import tw.kotlin.core.ui.SignInScreen
import tw.kotlin.core.ui.SignUpScreen
import tw.kotlin.core.ui.SuccessScreen
import tw.kotlin.core.ui.WelcomeScreen
import tw.kotlin.mopcon2022.MainViewModel
import tw.kotlin.mopcon2022.MainViewModelFactory

@Composable
fun MOPCON2022App() {
    val viewModel: MainViewModel = viewModel(
        factory = MainViewModelFactory(RestApiImpl(urlString = "http://10.0.2.2:8080"))
    )
    val uiState by viewModel.uiState.collectAsState()

    when (uiState.currentScreen) {
        NavDestinations.Welcome -> WelcomeScreen(
            onSignIn = viewModel::qrcode,
            onSignUp = { viewModel.nav(NavDestinations.SignUp) }
        )
        NavDestinations.SignIn -> SignInScreen(
            byteArray = uiState.qrCode,
            onSignIn = viewModel::signIn,
            onBackPressed = { viewModel.nav(NavDestinations.Welcome) }
        )
        NavDestinations.SignUp -> SignUpScreen(
            onSignUp = viewModel::signUp,
            onBackPressed = { viewModel.nav(NavDestinations.Welcome) }
        )
        NavDestinations.Success -> SuccessScreen()
    }

}