package tw.kotlin.core.network

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import tw.kotlin.core.network.model.UserLoginReqDTO
import tw.kotlin.core.network.model.UserSignupReqDTO

interface RestApi {
    suspend fun signup(userSignupReqDTO: UserSignupReqDTO): HttpResponse
    suspend fun qrcode(username: String): HttpResponse
    suspend fun login(userLoginReqDTO: UserLoginReqDTO): HttpResponse
}

class RestApiImpl(
    private val urlString: String
) : RestApi {
    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json()
        }
        defaultRequest {
            url(urlString)
        }
        expectSuccess = true
    }

    override suspend fun signup(userSignupReqDTO: UserSignupReqDTO): HttpResponse =
        httpClient.post("/user/signup") {
            setBody(userSignupReqDTO)
            contentType(ContentType.Application.Json)
        }

    override suspend fun qrcode(username: String): HttpResponse =
        httpClient.get("/user/qrcode") {
            parameter("username", username)
            contentType(ContentType.Image.PNG)
        }

    override suspend fun login(userLoginReqDTO: UserLoginReqDTO): HttpResponse =
        httpClient.post("/user/login") {
            setBody(userLoginReqDTO)
            contentType(ContentType.Application.Json)
        }

}