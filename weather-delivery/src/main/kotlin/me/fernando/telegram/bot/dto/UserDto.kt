package me.fernando.telegram.bot.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class UserDto(
    @JsonProperty("id") val id: Int,
    @JsonProperty("first_name") val firstName: String,
    @JsonProperty("last_name") val lastName: String?,
    @JsonProperty("username") val username: String?
)
