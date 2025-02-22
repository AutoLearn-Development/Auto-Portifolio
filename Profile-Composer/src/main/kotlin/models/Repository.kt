package models

data class Repository(
    val name: String,
    val description: String,
    val html_url: String,
    val url: String,
    val created_at: String
){
   // val createdDate: LocalDate = Instant.parse(created_at).atZone(ZoneId.of("America/Sao_Paulo")).toLocalDate()

    var readmeContent: String? = null

    val readmeImages: MutableList<Any> = mutableListOf()
}