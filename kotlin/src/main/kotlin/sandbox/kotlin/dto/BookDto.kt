package sandbox.kotlin.dto

class BookDto(
    val isbn: String,
    val title: String,
    val description: String,
    val image: String,
    val author: AuthorDto,
)