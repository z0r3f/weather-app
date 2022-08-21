package me.fernando.telegram.domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Chat(
    @Id var id: Long = 0L,
    @Column var title: String? = null,
    @Column var username: String? = null,
)
