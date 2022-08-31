package me.fernando.telegram.bot.job

import io.archimedesfw.usecase.UseCaseBus
import io.micronaut.scheduling.annotation.Scheduled
import jakarta.inject.Singleton
import me.fernando.chat.db.adapter.ChatAdapterRepository
import me.fernando.telegram.usecase.ForecastCmd
import org.slf4j.LoggerFactory
import java.time.LocalTime

@Singleton
class HourlyJob(
    private val chatAdapterRepository: ChatAdapterRepository,
    private val bus: UseCaseBus,
) {

    @Scheduled(cron = "0 * * * * *")
    fun execute() {
        LOG.debug("Hourly job started")
        val chats = chatAdapterRepository.getAlerts(getHourSystemNow())
        LOG.debug("Found {} chats to alert", chats.size)
        chats.forEach { bus(ForecastCmd(it)) }
        LOG.debug("Hourly job finished")
    }

    private fun getHourSystemNow() = LocalTime.now().hour

    private companion object {
        private val LOG = LoggerFactory.getLogger(HourlyJob::class.java)
    }
}
