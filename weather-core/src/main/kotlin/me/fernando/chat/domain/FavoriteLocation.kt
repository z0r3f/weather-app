package me.fernando.chat.domain

data class FavoriteLocation (
    var id: Long? = null,
    var chat: Chat? = null,
    var name: String? = null,
    var latitude: Double? = null,
    var longitude: Double? = null,
    var country: String? = null,
)
