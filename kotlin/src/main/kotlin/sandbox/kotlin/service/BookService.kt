package sandbox.kotlin.service

import sandbox.kotlin.domain.BookSummary
import sandbox.kotlin.domain.BookUpdateRequest
import sandbox.kotlin.entities.BookEntity

interface BookService {
    fun createUpdate(isbn: String, bookSummary: BookSummary): Pair<BookEntity, Boolean>
    fun list(authorId: Long? = null): List<BookEntity>
    fun get(isbn: String): BookEntity?
    fun partial(isbn: String, bookUpdateRequest: BookUpdateRequest): BookEntity
    fun delete(isbn: String)
}