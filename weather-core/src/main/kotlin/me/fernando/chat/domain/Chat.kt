package me.fernando.chat.domain

data class Chat(
    var id: Long = 0L,
    var title: String? = null,
    var username: String? = null,
    var favoriteLocations: Set<FavoriteLocation> = mutableSetOf()
)
