package io.github.z0r3f.weather.infrastructure.rest.telegram.dto

open class MessageDtoMother {
    open fun of(
        messageId: Long? = null,
        from: UserDto? = null,
        date: Long? = null,
        chat: ChatDto? = null,
        text: String? = null,
        replyMarkup: ReplyMarkupDto? = null,
    ): MessageDto = MessageDto(
        messageId = messageId ?: 1L,
        from = from ?: UserDtoMother().of(),
        date = date ?: 1661785532L,
        chat = chat ?: ChatDtoMother().of(),
        text = text ?: "text",
        replyMarkup = replyMarkup,
    )
}
