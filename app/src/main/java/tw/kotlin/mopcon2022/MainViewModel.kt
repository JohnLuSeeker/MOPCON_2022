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

class MainViewModel(
    private val api: RestApi
) : ViewModel() {
    private var _username:String = ""
    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        MainUiState()
    )

    fun nav(navDestination: NavDestinations) {
        _uiState.value = _uiState.value.copy(
            currentScreen = navDestination
        )
    }

    fun signUp(username: String, password: String) {
        viewModelScope.launch {
            kotlin.runCatching {
                api.signup(
                    UserSignupReqDTO(
                        username = username,
                        password = password
                    )
                )
            }.onSuccess {
                nav(NavDestinations.SignIn)
            }.onFailure {
                Log.e("MainViewModel", it.toString())
            }
        }
    }

    @OptIn(InternalAPI::class)
    fun qrcode(username: String) {
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

    fun signIn(password: String, code: String) {
        viewModelScope.launch {
            kotlin.runCatching {
                Log.d("VVV", "${uiState.value.username} $password $code")
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
        return MainViewModel(api) as T
    }
}