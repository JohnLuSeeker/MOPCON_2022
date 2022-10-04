package tw.kotlin.mopcon2022.ui

import androidx.compose.runtime.Immutable
import tw.kotlin.core.ui.theme.StringResource.password

enum class NavDestinations {
    Home, SignUp, SignIn, Welcome
}

@Immutable
data class MainUiState(
    val currentScreen: NavDestinations = NavDestinations.Home,
    val username: String = "",
    val qrCode: ByteArray = byteArrayOf()
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MainUiState

        if (currentScreen != other.currentScreen) return false
        if (username != other.username) return false
        if (!qrCode.contentEquals(other.qrCode)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = currentScreen.hashCode()
        result = 31 * result + username.hashCode()
        result = 31 * result + password.hashCode()
        result = 31 * result + qrCode.contentHashCode()
        return result
    }
}