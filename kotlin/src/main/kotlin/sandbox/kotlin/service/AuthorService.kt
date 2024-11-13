package sandbox.kotlin.service

import sandbox.kotlin.domain.AuthorUpdateRequest
import sandbox.kotlin.entities.AuthorEntity

interface AuthorService {
    fun create(authorEntity: AuthorEntity): AuthorEntity
    fun list(): List<AuthorEntity>
    fun get(id: Long): AuthorEntity?
    fun fullUpdate(id: Long, authorEntity: AuthorEntity): AuthorEntity
    fun partialUpdate(id: Long, authorUpdateRequest: AuthorUpdateRequest): AuthorEntity
    fun delete(id: Long)
}
