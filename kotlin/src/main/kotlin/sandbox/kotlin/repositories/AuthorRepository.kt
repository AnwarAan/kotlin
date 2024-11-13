package sandbox.kotlin.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import sandbox.kotlin.entities.AuthorEntity

@Repository
interface AuthorRepository : JpaRepository<AuthorEntity, Long>


