package me.fernando.chat.db.entities

import javax.persistence.*

@Entity
@Table(name = "alert")
open class AlertEntity (
    @Id
    @SequenceGenerator(name = "alert_seq", sequenceName = "alert_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "alert_seq")
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
