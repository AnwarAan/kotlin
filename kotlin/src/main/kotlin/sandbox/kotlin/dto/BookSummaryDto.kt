package sandbox.kotlin.dto

class BookSummaryDto(
    val isbn: String,
    val title: String,
    val description: String,
    val image: String,
    val author: AuthorSummaryDto
    ,
)