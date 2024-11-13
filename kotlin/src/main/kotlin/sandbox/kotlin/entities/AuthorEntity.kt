package sandbox.kotlin.entities

import jakarta.persistence.*

@Entity
@Table(name = "authors")
data class AuthorEntity(
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "author_iq_seq")
    val id: Long?,

    @Column(name = "name")
    val name: String,

    @Column(name = "age")
    val age: Int,

    @Column(name = "description")
    val description: String,

    @Column(name = "image")
    val image: String,

    @OneToMany(mappedBy = "authorEntity")
    val bookEntities: List<AuthorEntity> = emptyList(),


    ) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as AuthorEntity
        if (id != other.id) return false
        if (name != other.name) return false
        if (age != other.age) return false
        if (description != other.description) return false
        if (image != other.image) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + name.hashCode()
        result = 31 * result + age.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + image.hashCode()
        result = 31 * result + bookEntities.hashCode()
        return result
    }
}

