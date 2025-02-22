package api

import com.google.gson.Gson
import models.User
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

class ApiService(
    val user: String
){
    val client: HttpClient = HttpClient.newHttpClient()

    val gson = Gson()

    fun getUserData(): User{
        val uri : URI = URI.create("https://api.github.com/users/$user")

        val request = HttpRequest.newBuilder()
            .uri(uri).build()

        var response: HttpResponse<String>? = null
        val requisition = runCatching {response = client.send(request, HttpResponse.BodyHandlers.ofString())}

        requisition.onFailure {
            throw IllegalArgumentException("Não foi possível se conectar com github")
        }


        if (response?.statusCode() != 200) {
            throw IllegalArgumentException("Não foi possível encontrar dados deste usuário")
        }

        val responseBody = response!!.body()
        return gson.fromJson(responseBody, User::class.java)
    }

    fun getAllRepositoryData(){

    }
}