import api.ApiService
import models.Repository
import models.User

fun main() {
    val apiService = ApiService("Gus1331")

    val userData: User = apiService.getUserData()
    val repData: List<Repository> = apiService.getAllRepositoryData()

    println(repData)
}