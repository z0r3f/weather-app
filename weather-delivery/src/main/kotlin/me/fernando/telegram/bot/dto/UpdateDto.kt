package me.fernando.telegram.bot.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class UpdateDto(
    @JsonProperty("update_id") val updateId: Long,
    @JsonProperty("message") val message: MessageDto?,
    @JsonProperty("edited_message") val editedMessage: MessageDto?,
    @JsonProperty("channel_post") val channelPost: MessageDto?,
    @JsonProperty("edited_channel_post") val editedChannelPost: MessageDto?,
//    @JsonProperty("inline_query") val inlineQuery: InlineQueryDto?,
//    @JsonProperty("chosen_inline_result") val chosenInlineResult: ChosenInlineResultDto?,
//    @JsonProperty("callback_query") val callbackQuery: CallbackQueryDto?,
//    @JsonProperty("shipping_query") val shippingQuery: ShippingQueryDto?,
//    @JsonProperty("pre_checkout_query") val preCheckoutQuery: PreCheckoutQueryDto?,
//    @JsonProperty("poll") val poll: PollDto?,
//    @JsonProperty("poll_answer") val pollAnswer: PollAnswerDto?,
//    @JsonProperty("my_chat_members") val myChatMembers: ChatMemberUpdatedDto?,
//    @JsonProperty("chat_member") val chatMember: ChatMemberUpdatedDto?,
//    @JsonProperty("chat_join_request") val chatJoinRequest: ChatJoinRequestDto?,
)
