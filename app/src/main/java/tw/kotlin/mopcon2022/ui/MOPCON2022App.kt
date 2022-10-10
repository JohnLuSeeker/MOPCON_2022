package tw.kotlin.mopcon2022.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import tw.kotlin.core.network.RestApiImpl
import tw.kotlin.core.ui.HomeScreen
import tw.kotlin.core.ui.SignInScreen
import tw.kotlin.core.ui.SignUpScreen
import tw.kotlin.core.ui.SignUpSuccessScreen
import tw.kotlin.core.ui.WelcomeScreen
import tw.kotlin.mopcon2022.MainViewModelFactory
import tw.kotlin.mopcon2022.MainViewModelImpl

@Composable
fun MOPCON2022App() {
    val viewModel: MainViewModelImpl = viewModel(
        factory = MainViewModelFactory(RestApiImpl(urlString = "http://10.0.2.2:8080"))
    )
    val uiState by viewModel.uiState.collectAsState()

    when (uiState.currentScreen) {
        NavDestinations.Home -> HomeScreen(
            navToSignIn = { viewModel.nav(NavDestinations.SignIn) },
            onSignUp = { viewModel.nav(NavDestinations.SignUp) }
        )
        NavDestinations.SignIn -> SignInScreen(
            onSignIn = viewModel::signIn,
            onBackPressed = { viewModel.nav(NavDestinations.Home) }
        )
        NavDestinations.SignUp -> SignUpScreen(
            onSignUp = viewModel::signUp,
            onBackPressed = { viewModel.nav(NavDestinations.Home) }
        )
        NavDestinations.SignUpSucceed -> SignUpSuccessScreen(
            byteArray = uiState.qrCode,
            navToSignIn = { viewModel.nav(NavDestinations.SignIn) }
        )
        NavDestinations.Welcome -> WelcomeScreen()
    }

}