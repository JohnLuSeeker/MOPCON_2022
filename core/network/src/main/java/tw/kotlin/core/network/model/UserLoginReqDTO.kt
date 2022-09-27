package tw.kotlin.core.network.model

import kotlinx.serialization.Serializable

@Serializable
data class UserLoginReqDTO(
    val username: String,
    val password: String,
    val code: String,
)