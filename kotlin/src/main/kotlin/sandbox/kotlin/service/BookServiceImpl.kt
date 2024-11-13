package sandbox.kotlin.service

import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import sandbox.kotlin.domain.AuthorUpdateRequest
import sandbox.kotlin.domain.BookSummary
import sandbox.kotlin.domain.BookUpdateRequest
import sandbox.kotlin.entities.AuthorEntity
import sandbox.kotlin.entities.BookEntity
import sandbox.kotlin.repositories.AuthorRepository
import sandbox.kotlin.repositories.BookRepository
import sandbox.kotlin.toBookEntity

@Service
class BookServiceImpl(private val bookRepository: BookRepository,val authorRepository: AuthorRepository) : BookService {
    override fun get(isbn: String): BookEntity? {
        return bookRepository.findByIdOrNull(isbn)
    }

    override fun list(authorId: Long?): List<BookEntity> {
        return  bookRepository.findAll()
    }

    @Transactional
    override fun createUpdate(isbn: String, bookSummary: BookSummary): Pair<BookEntity, Boolean> {
        val book = bookSummary.copy(isbn = isbn)
        val exist = bookRepository.existsById(isbn)

        val author = authorRepository.findByIdOrNull(book.author.id)
        checkNotNull(author)

        val save = bookRepository.save(book.toBookEntity(author))
        return Pair(save, !exist)
    }

    override fun delete(isbn: String, bookUpdateRequest: BookUpdateRequest): BookEntity {
        val exist=bookRepository.findByIdOrNull(isbn)
        checkNotNull(exist)

        val update=exist.copy(
            title = bookUpdateRequest.title?:exist.title,
            description = bookUpdateRequest.description?:exist.description,
            image = bookUpdateRequest.image ?: exist.image
        )

        return  bookRepository.save(update)
    }
}