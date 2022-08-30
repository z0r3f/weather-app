package me.fernando.telegram.usecase

import io.archimedesfw.usecase.Command
import me.fernando.chat.domain.Chat

class CallbackUnknownCmd(
    private val chat: Chat,
) : Command<Unit>() {
    override fun run() {
        run(SendMessageCmd(chat, "Callback unknown"))
    }
}
