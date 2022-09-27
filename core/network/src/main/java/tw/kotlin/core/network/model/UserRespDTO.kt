package tw.kotlin.core.network.model

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class UserRespDTO(
    val username: String,
    val createDate: LocalDateTime
)