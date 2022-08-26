package me.fernando.chat.db.entities

import javax.persistence.*

@Entity
@Table(name = "location_favorite")
open class FavoriteLocationEntity(
    @Id
    @SequenceGenerator(name = "location_favorite_seq", sequenceName = "location_favorite_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "location_favorite_seq")
    @Column(name = "id", updatable = false)
    var id: Long? = null,

    @Column
    var name: String? = null,

    @Column
    var latitude: Double? = null,

    @Column
    var longitude: Double? = null,

    @Column
    var country: String? = null,
) {
    @ManyToOne
    var chat: ChatEntity? = null

    override fun toString(): String {
        return "${this::class.simpleName}(id=$id, name='$name', latitude=$latitude, longitude=$longitude, country='$country')"
    }
}

