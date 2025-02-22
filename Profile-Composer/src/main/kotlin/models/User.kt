package models

data class User(
    val login: String,
    val html_url: String,
    val avatar_url: String,
    val location: String,
    val repos_url: String,
    val public_repos: String,
    val bio: String
)
