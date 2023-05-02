package io.github.z0r3f.weather.infrastructure.rest.telegram.dto

open class ChatPhotoDtoMother {
    open fun of(
        smallFileId: String? = null,
        bigFileId: String? = null,
        smallFileUniqueId: String? = null,
        bigFileUniqueId: String? = null,
    ) = ChatPhotoDto(
        smallFileId = smallFileId ?: "small-file-id",
        bigFileId = bigFileId ?: "big-field-id",
        smallFileUniqueId = smallFileUniqueId ?: "small-file-unique-id",
        bigFileUniqueId = bigFileUniqueId ?: "big-file-unique-id",
    )
}
