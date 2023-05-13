package io.github.z0r3f.weather.infrastructure.rest.telegram.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class UserDto(
    @JsonProperty("id") val id: Int,
    @JsonProperty("is_bot") val isBot: Boolean,
    @JsonProperty("first_name") val firstName: String,
    @JsonProperty("last_name") val lastName: String?,
    @JsonProperty("username") val username: String?,
    @JsonProperty("language_code") val languageCode: String?
)
