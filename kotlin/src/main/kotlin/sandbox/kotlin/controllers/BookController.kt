package sandbox.kotlin.controllers

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import sandbox.kotlin.dto.BookSummaryDto
import sandbox.kotlin.dto.BookUpdateRequestDto
import sandbox.kotlin.service.BookService
import sandbox.kotlin.toBookSummary
import sandbox.kotlin.toBookSummaryDto
import sandbox.kotlin.toBookUpdateRequest

@RestController()
@RequestMapping(path = ["/v1/books"])
class BookController(val bookService: BookService) {
    @PutMapping
    fun createFullUpdateBook(
        @PathVariable("isbn") isbn: String,
        @RequestBody book: BookSummaryDto
    ): ResponseEntity<BookSummaryDto> {
        try {
            val (save, isCreated) = bookService.createUpdate(isbn, book.toBookSummary())
            if (isCreated) HttpStatus.CREATED else HttpStatus.OK
            return ResponseEntity(save.toBookSummaryDto(), HttpStatus.OK)
        } catch (ex: IllegalArgumentException) {
            return ResponseEntity(HttpStatus.BAD_REQUEST)
        }
    }

    @GetMapping
    fun findBooks(
        @RequestParam("author") authorId: Long,
    ): List<BookSummaryDto> {
        return bookService.list(authorId).map { it.toBookSummaryDto() }
    }

    @GetMapping(path = ["/{isbn}"])
    fun findBookById(
        @PathVariable("isbn") isbn: String,
    ): ResponseEntity<BookSummaryDto> {
        return bookService.get(isbn)?.let { ResponseEntity(it.toBookSummaryDto(), HttpStatus.OK) } ?: ResponseEntity(
            HttpStatus.NOT_FOUND
        )
    }

    @PatchMapping
    fun patchBook(
        @PathVariable("isbn") isbn: String,
        @RequestBody bookUpdateRequestDto: BookUpdateRequestDto
    ): ResponseEntity<BookSummaryDto> {
        try {
            val update = bookService.partial(isbn, bookUpdateRequestDto.toBookUpdateRequest())
            return ResponseEntity(update.toBookSummaryDto(), HttpStatus.OK)
        } catch (ex: IllegalArgumentException) {
            return ResponseEntity(HttpStatus.BAD_REQUEST)
        }
    }

    @DeleteMapping(path = ["/{isbn}"])
    fun deleteBook(
        @PathVariable("isbn") isbn: String,
    ): ResponseEntity<Unit> {
        bookService.delete(isbn)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}