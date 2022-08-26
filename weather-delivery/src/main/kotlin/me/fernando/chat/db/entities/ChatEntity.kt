package me.fernando.chat.db.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
@Table(name = "chat")
class ChatEntity(
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    var id: Long = 0L,

    @Column(name = "title", nullable = true)
    var title: String? = null,

    @Column(name = "username", nullable = true)
    var username: String? = null
) {
    @JsonIgnore
    @OneToMany(mappedBy = "chat")
    var favoriteLocations: MutableSet<FavoriteLocationEntity> = mutableSetOf()

    override fun toString(): String {
        return "${this::class.simpleName}(id=$id, title=$title, username=$username)"
    }
}
