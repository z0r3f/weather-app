package me.fernando.weather.service

import jakarta.inject.Singleton
import me.fernando.telegram.domain.BotCommandType
import me.fernando.util.trimLeadingSpaces

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
        BotCommandType.values().forEach {
            text += """
                *${it.command}* - ${it.description}
                """
        }
        return text.trimLeadingSpaces()
    }
}
