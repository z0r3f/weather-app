package io.github.z0r3f.weather.infrastructure.rest.telegram.dto

open class UserDtoMother {
    open fun of(
        id: Int? = null,
        isBot: Boolean? = null,
        firstName: String? = null,
        lastName: String? = null,
        username: String? = null,
        languageCode: String? = null,
    ): UserDto = UserDto(
        id = id ?: 1,
        isBot = isBot ?: false,
        firstName = firstName ?: "John",
        lastName = lastName ?: "Hyde",
        username = username ?: "johnihyde",
        languageCode = languageCode ?: "en"
    )
}
