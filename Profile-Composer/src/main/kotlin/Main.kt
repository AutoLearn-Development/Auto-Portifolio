import api.ApiService
import models.User

fun main() {
    val apiService = ApiService("Gus1331")

    val userData: User = apiService.getUserData()
    println(userData.login)
}