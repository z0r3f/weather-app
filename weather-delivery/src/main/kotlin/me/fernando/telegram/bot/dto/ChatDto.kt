package me.fernando.telegram.bot.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class ChatDto(
    @JsonProperty("id") val id: Long,
    @JsonProperty("type") val type: String,
    @JsonProperty("title") val title: String?,
    @JsonProperty("username") val username: String?,
    @JsonProperty("first_name") val firstName: String?,
    @JsonProperty("last_name") val lastName: String?,
    @JsonProperty("all_members_are_administrators") val allMembersAreAdministrators: Boolean?,
    @JsonProperty("photo") val photo: ChatPhotoDto?,
    @JsonProperty("description") val description: String?,
    @JsonProperty("invite_link") val inviteLink: String?,
    @JsonProperty("pinned_message") val pinnedMessage: MessageDto?,
    @JsonProperty("sticker_set_name") val stickerSetName: String?,
    @JsonProperty("can_set_sticker_set") val canSetStickerSet: Boolean?

)
