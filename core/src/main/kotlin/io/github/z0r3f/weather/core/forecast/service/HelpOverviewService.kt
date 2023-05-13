package io.github.z0r3f.weather.core.forecast.service

import jakarta.inject.Singleton
import io.github.z0r3f.weather.core.telegram.domain.message.BotMessageType
import io.github.z0r3f.weather.core.util.trimLeadingSpaces

@Singleton
class HelpOverviewService: OverviewService<Unit> {

    override fun generateOverviewMessage(data: Unit): String = """
            ${getSummary()}
            ${getCommands()}
            """.trimLeadingSpaces()

    private fun getSummary() = """            
            *I can help you with the weather 🌤 in any city in the world 🗺. Just type the name of the city and I will tell you the weather.*
    
            Use the following commands:
        """

    private fun getCommands(): String {
        var text = ""
        BotMessageType.getAvailableBotMessageType().forEach {
            text += """
                ${it.toMarkdown()}
                """
        }
        return text.trimLeadingSpaces()
    }
}
