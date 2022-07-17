package me.fernando.telegram.bot.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class ChatPhotoDto(
    @JsonProperty("small_file_id") val smallFileId: String,
    @JsonProperty("big_file_id") val bigFileId: String,
    @JsonProperty("small_file_unique_id") val smallFileUniqueId: String,
    @JsonProperty("big_file_unique_id") val bigFileUniqueId: String,
)
