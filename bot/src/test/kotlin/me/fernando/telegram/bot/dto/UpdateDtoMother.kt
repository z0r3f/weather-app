package me.fernando.telegram.bot.dto

open class UpdateDtoMother {
    open fun of(
        updateId: Long? = null,
        message: MessageDto? = null,
        editedMessage: MessageDto? = null,
        channelPost: MessageDto? = null,
        editedChannelPost: MessageDto? = null,
        callbackQuery: CallbackQueryDto? = null,
    ) = UpdateDto(
        updateId = updateId ?: 1L,
        message = message ?: MessageDtoMother().of(),
        editedMessage = editedMessage ?: MessageDtoMother().of(),
        channelPost = channelPost ?: MessageDtoMother().of(),
        editedChannelPost = editedChannelPost ?: MessageDtoMother().of(),
        callbackQuery = callbackQuery,
    )
}
