package me.fernando.telegram.bot.dto

open class ChatDtoMother {
    open fun of(
        id: Long? = null,
        type: String? = null,
        title: String? = null,
        username: String? = null,
        firstName: String? = null,
        lastName: String? = null,
        allMembersAreAdministrators: Boolean? = null,
        photo: ChatPhotoDto? = null,
        description: String? = null,
        inviteLink: String? = null,
        pinnedMessage: MessageDto? = null,
        stickerSetName: String? = null,
        canSetStickerSet: Boolean? = null,
    ): ChatDto = ChatDto(
        id = id ?: 1L,
        type = type ?: "private",
        title = title ?: "title",
        username = username ?: "johnihyde",
        firstName = firstName ?: "John",
        lastName = lastName ?: "Hyde",
        allMembersAreAdministrators = allMembersAreAdministrators ?: true,
        photo = photo ?: ChatPhotoDtoMother().of(),
        description = description ?: "description",
        inviteLink = inviteLink ?: "invite-link",
        pinnedMessage = pinnedMessage,
        stickerSetName = stickerSetName ?: "sticker-set-name",
        canSetStickerSet = canSetStickerSet ?: true
    )
}
