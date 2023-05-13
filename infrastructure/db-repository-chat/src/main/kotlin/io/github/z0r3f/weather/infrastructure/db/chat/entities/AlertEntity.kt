package io.github.z0r3f.weather.infrastructure.db.chat.entities

import javax.persistence.*

@Entity
@Table(name = "alert")
data class AlertEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    var id: Long? = null,

    @Column(name = "hour_of_day")
    var hourOfDay: Int? = null,
) {
    @ManyToOne
    var chat: ChatEntity? = null

    override fun toString(): String {
        return "${this::class.simpleName}(id=$id, hourOfDay='$hourOfDay')"
    }
}
