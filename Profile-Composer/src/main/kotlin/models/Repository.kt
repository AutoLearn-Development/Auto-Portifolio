package models

import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

data class Repository(
    val name: String,
    val description: String,
    val html_url: String,
    val url: String,
    private val created_at: String
){
    val createdDate: LocalDate = Instant.parse(created_at).atZone(ZoneId.of("America/Sao_Paulo")).toLocalDate()
}