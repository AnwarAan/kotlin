package sandbox.kotlin.controllers

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import sandbox.kotlin.dto.AuthorDto
import sandbox.kotlin.dto.AuthorUpdateRequestDto
import sandbox.kotlin.service.AuthorService
import sandbox.kotlin.toAuthorDto
import sandbox.kotlin.toAuthorEntity
import sandbox.kotlin.toAuthorUpdateRequest

@RestController
@RequestMapping(path = ["/v1/authors"])
class AuthorController(private val authorService: AuthorService) {

    @PostMapping
    fun createAuthor(@RequestBody authorDto: AuthorDto): ResponseEntity<AuthorDto> {
        return try {
            val createAuthor = authorService.create(authorDto.toAuthorEntity()).toAuthorDto()
            ResponseEntity(createAuthor, HttpStatus.CREATED)
        } catch (ex: IllegalArgumentException) {
            ResponseEntity(HttpStatus.BAD_REQUEST)
        }
    }

    @GetMapping
    fun readManyAuthor(): List<AuthorDto> {
        return authorService.list().map { it.toAuthorDto() }
    }

    @GetMapping(path = ["/{id}"])
    fun readOneAuthor(@PathVariable("id") id: Long): ResponseEntity<AuthorDto> {
        val findAuthor = authorService.get(id)?.toAuthorDto()
        return findAuthor?.let {
            ResponseEntity(it, HttpStatus.OK)
        } ?: ResponseEntity(HttpStatus.NOT_FOUND)
    }

    @PutMapping(path = ["/{id}"])
    fun fullUpdateAuthor(@PathVariable("id") id: Long, @RequestBody authorDto: AuthorDto): ResponseEntity<AuthorDto> {
        return try {
            val updateAuthor = authorService.fullUpdate(id, authorDto.toAuthorEntity())
            ResponseEntity(updateAuthor.toAuthorDto(), HttpStatus.OK)
        } catch (ex: IllegalArgumentException) {
            ResponseEntity(HttpStatus.BAD_REQUEST)
        }
    }

    @PatchMapping(path = ["/{id}"])
    fun partialUpdateAuthor(
        @PathVariable("id") id: Long,
        @RequestBody authorUpdateRequest: AuthorUpdateRequestDto
    ): ResponseEntity<AuthorDto> {
        return try {
            val updateAuthor = authorService.partialUpdate(id, authorUpdateRequest.toAuthorUpdateRequest())
            ResponseEntity(updateAuthor.toAuthorDto(), HttpStatus.OK)
        } catch (ex: IllegalArgumentException) {
            ResponseEntity(HttpStatus.BAD_REQUEST)
        }
    }

    @DeleteMapping(path = ["/{id}"])
    fun deleteAuthor(@PathVariable("id") id: Long): ResponseEntity<Unit> {
        authorService.delete(id)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}