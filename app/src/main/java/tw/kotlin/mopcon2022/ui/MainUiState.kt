package tw.kotlin.mopcon2022.ui

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

enum class NavDestinations {
    Welcome, SignUp, SignIn, Success
}

@Immutable
data class MainUiState(
    val currentScreen: NavDestinations = NavDestinations.Welcome,
    val username: String = "",
    val password: String = "",
    val code: String = "",
    val qrCode: ByteArray = byteArrayOf()
) {
    var QRCode by mutableStateOf(qrCode)
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MainUiState

        if (currentScreen != other.currentScreen) return false
        if (username != other.username) return false
        if (password != other.password) return false
        if (code != other.code) return false
        if (!qrCode.contentEquals(other.qrCode)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = currentScreen.hashCode()
        result = 31 * result + username.hashCode()
        result = 31 * result + password.hashCode()
        result = 31 * result + code.hashCode()
        result = 31 * result + qrCode.contentHashCode()
        return result
    }
}