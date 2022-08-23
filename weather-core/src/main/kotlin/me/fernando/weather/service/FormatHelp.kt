package me.fernando.weather.service

import me.fernando.telegram.domain.BotCommand
import me.fernando.util.trimLeadingSpaces

class FormatHelp {

    companion object {
        fun overview() = """
            ${getSummary()}
            ${getCommands()}
            """.trimLeadingSpaces()

//        "(^ +)|( +$)"

        private fun getSummary() = """            
            *I can help you with the weather 🌤 in any city in the world 🗺. Just type the name of the city and I will tell you the weather.*
    
            Use the following commands:
        """

        private fun getCommands(): String {
            var text = ""
            BotCommand.values().forEach {
                text += """
                *${it.command}* - ${it.description}
                """
            }
            return text.trimLeadingSpaces()
        }
    }
}
