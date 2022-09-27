package tw.kotlin.core.network.model

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class User(
    val username: String,
    val createDate: LocalDateTime,
)