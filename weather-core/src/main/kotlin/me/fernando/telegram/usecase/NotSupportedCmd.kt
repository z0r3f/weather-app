package me.fernando.telegram.usecase

import io.archimedesfw.usecase.Command
import me.fernando.chat.domain.Chat

class NotSupportedCmd(
    private val chat: Chat,
) : Command<Unit>() {
    override fun run() {
        run(SendMessageCmd(chat, "Command not supported"))
    }
}
