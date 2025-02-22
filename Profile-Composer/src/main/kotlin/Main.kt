import api.GitHubApiService
import models.Repository
import models.User

fun main() {
    val gitHubApiService = GitHubApiService("Gus1331")

    val userData: User = gitHubApiService.getUserData()
    val repData: List<Repository> = gitHubApiService.getAllRepositoryData()

    repData.forEach {
        gitHubApiService.getREADME(it)
    }

}