package tw.kotlin.mopcon2022

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import io.ktor.util.InternalAPI
import io.ktor.util.toByteArray
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import tw.kotlin.core.network.RestApi
import tw.kotlin.core.network.model.UserLoginReqDTO
import tw.kotlin.core.network.model.UserSignupReqDTO
import tw.kotlin.mopcon2022.ui.MainUiState
import tw.kotlin.mopcon2022.ui.NavDestinations

interface MainViewModel {
    val uiState: StateFlow<MainUiState>
    fun nav(navDestination: NavDestinations)
    fun signUp(username: String, password: String)
    fun qrcode(username: String)
    fun signIn(password: String, code: String)
}

class MainViewModelImpl(
    private val api: RestApi
) : ViewModel(), MainViewModel {
    private val _uiState = MutableStateFlow(MainUiState())
    override val uiState: StateFlow<MainUiState> = _uiState.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        MainUiState()
    )

    override fun nav(navDestination: NavDestinations) {
        _uiState.value = _uiState.value.copy(
            currentScreen = navDestination
        )
    }

    override fun signUp(username: String, password: String) {
        viewModelScope.launch {
            kotlin.runCatching {
                api.signup(
                    UserSignupReqDTO(
                        username = username,
                        password = password
                    )
                )
            }.onSuccess {
                qrcode(username)
            }.onFailure {
                Log.e("MainViewModel", it.toString())
            }
        }
    }

    @OptIn(InternalAPI::class)
    override fun qrcode(username: String) {
        viewModelScope.launch {
            kotlin.runCatching {
                api.qrcode(
                    username = username
                )
            }.onSuccess {
                _uiState.value = _uiState.value.copy(
                    username = username,
                    qrCode = it.content.toByteArray()
                )
                nav(NavDestinations.SignIn)
            }.onFailure {
                Log.e("MainViewModel", it.toString())
            }
        }
    }

    override fun signIn(password: String, code: String) {
        viewModelScope.launch {
            kotlin.runCatching {
                api.login(
                    UserLoginReqDTO(
                        username = uiState.value.username,
                        password = password,
                        code = code
                    )
                )
            }.onSuccess {
                nav(NavDestinations.Success)
            }.onFailure {
                Log.e("MainViewModel", it.toString())
            }
        }
    }
}

class MainViewModelFactory(private val api: RestApi) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModelImpl(api) as T
    }
}