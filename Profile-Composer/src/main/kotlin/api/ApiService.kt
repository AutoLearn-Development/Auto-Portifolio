package api

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import models.Repository
import models.User
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

class ApiService(
    val user: String
) {
    private val client: HttpClient = HttpClient.newHttpClient()

    private val gson = Gson()

    private fun sendRequisition(uri: URI): String {
        val request = HttpRequest.newBuilder()
            .uri(uri).build()

        var response: HttpResponse<String>? = null
        val requisition = runCatching { response = client.send(request, HttpResponse.BodyHandlers.ofString()) }

        requisition.onFailure {
            throw IllegalArgumentException("Não foi possível se conectar com github")
        }


        if (response?.statusCode() != 200) {
            throw IllegalArgumentException("Erro de requisicção: ${response?.statusCode()}")
        }

        // Thread.sleep(2000) // para reduzir tempo entre requisições

        return response!!.body()
    }

    fun getUserData(): User {
        val uri: URI = URI.create("https://api.github.com/users/$user")
        val responseBody: String = sendRequisition(uri)

        return gson.fromJson(responseBody, User::class.java)
    }

    fun getAllRepositoryData(): List<Repository> {
        val uri: URI = URI.create("https://api.github.com/users/$user/repos")
        val responseBody: String = sendRequisition(uri)

        val listType = object : TypeToken<List<Repository>>() {}.type

        return gson.fromJson(responseBody, listType)
    }

    fun getREADME(repository: Repository) {
        val uri: URI = URI.create("https://raw.githubusercontent.com/$user/${repository.name}/main/README.md")
        try {
            val responseBody: String = sendRequisition(uri)
            repository.readmeContent = responseBody

        } catch (e: IllegalArgumentException) {
            return
        }
    }
}