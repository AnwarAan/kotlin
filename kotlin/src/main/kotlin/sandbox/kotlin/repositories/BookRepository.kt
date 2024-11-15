package sandbox.kotlin.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import sandbox.kotlin.entities.BookEntity

@Repository
interface BookRepository : JpaRepository<BookEntity, String> {
    fun findByAuthorEntity(id: Long): List<BookEntity>
}


