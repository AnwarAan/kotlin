package sandbox.kotlin.service

import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import sandbox.kotlin.domain.AuthorUpdateRequest
import sandbox.kotlin.entities.AuthorEntity
import sandbox.kotlin.repositories.AuthorRepository

@Service
class AuthorServiceImpl(private val authorRepository: AuthorRepository) : AuthorService {
    override fun list(): List<AuthorEntity> {
        return authorRepository.findAll()
    }

    override fun create(authorEntity: AuthorEntity): AuthorEntity {
        require(null == authorEntity.id)
        return authorRepository.save(authorEntity)
    }

    override fun delete(id: Long) {
        return authorRepository.deleteById(id)
    }

    override fun get(id: Long): AuthorEntity? {
        return authorRepository.getReferenceById(id)
    }

    @Transactional
    override fun fullUpdate(id: Long, authorEntity: AuthorEntity): AuthorEntity {
        check(authorRepository.existsById(id))
        val update = authorEntity.copy(id = id)
        return authorRepository.save(update)
    }

    @Transactional
    override fun partialUpdate(id: Long, authorUpdateRequest: AuthorUpdateRequest): AuthorEntity {
        val exist = authorRepository.findByIdOrNull(id)
        checkNotNull(exist)

        val update = exist.copy(
            name = authorUpdateRequest.name ?: exist.name,
            age = authorUpdateRequest.age ?: exist.age,
            description = authorUpdateRequest.description ?: exist.description,
            image = authorUpdateRequest.image ?: exist.image,
        )

        return authorRepository.save(update)
    }
}
