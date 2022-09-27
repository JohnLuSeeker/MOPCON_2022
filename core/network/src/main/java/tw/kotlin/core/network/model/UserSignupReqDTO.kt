package tw.kotlin.core.network.model

import kotlinx.serialization.Serializable

@Serializable
data class UserSignupReqDTO(
    val username: String,
    val password: String,
)