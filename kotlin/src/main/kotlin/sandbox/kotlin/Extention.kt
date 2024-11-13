package sandbox.kotlin

import sandbox.kotlin.domain.AuthorSummary
import sandbox.kotlin.domain.AuthorUpdateRequest
import sandbox.kotlin.domain.BookSummary
import sandbox.kotlin.domain.BookUpdateRequest
import sandbox.kotlin.dto.*
import sandbox.kotlin.entities.AuthorEntity
import sandbox.kotlin.entities.BookEntity
import sandbox.kotlin.exception.InvalidAuthorException

fun AuthorEntity.toAuthorDto() = AuthorDto(
    id = this.id,
    name = this.name,
    age = this.age,
    description = this.description,
    image = this.image,
)

fun AuthorEntity.toAuthorSummaryDto(): AuthorSummaryDto {
    val authorId = this.id ?: throw InvalidAuthorException()
    return AuthorSummaryDto(
        id = authorId,
        name = this.name,
        image = this.image,
    )
}

fun AuthorDto.toAuthorEntity() = AuthorEntity(
    id = this.id,
    name = this.name,
    age = this.age,
    description = this.description,
    image = this.image,
)

fun AuthorUpdateRequestDto.toAuthorUpdateRequest() = AuthorUpdateRequest(
    id = this.id,
    name = this.name,
    age = this.age,
    description = this.description,
    image = this.image,
)

fun AuthorSummaryDto.toAuthorSummary() = AuthorSummary(
    id = this.id,
    name = this.name,
    image = this.image,
)

fun BookSummary.toBookEntity(author: AuthorEntity) = BookEntity(
    isbn = this.isbn,
    title = this.title,
    description = this.description,
    image = this.image,
    authorEntity = author
)

fun BookSummaryDto.toBookSummary() = BookSummary(
    isbn = this.isbn,
    title = this.title,
    description = this.description,
    image = this.image,
    author = this.author.toAuthorSummary(),
)

fun BookEntity.toBookSummaryDto() = BookSummaryDto(
    isbn = this.isbn,
    title = this.title,
    description = this.description,
    image = this.image,
    author = authorEntity.toAuthorSummaryDto(),
)

fun BookUpdateRequestDto.toBookUpdateRequest() = BookUpdateRequest(
    title = this.title,
    description = this.description,
    image = this.image,
)




