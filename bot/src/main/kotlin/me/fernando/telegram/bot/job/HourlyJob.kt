package me.fernando.telegram.bot.job

import io.archimedesfw.cqrs.ActionBus
import io.github.z0r3f.weather.core.forecast.cqrs.ForecastMessage
import io.micronaut.scheduling.annotation.Scheduled
import jakarta.inject.Singleton
import me.fernando.chat.db.adapter.ChatAdapterRepository
import org.slf4j.LoggerFactory
import java.time.ZoneId
import java.time.ZonedDateTime

@Singleton
class HourlyJob(
    private val chatAdapterRepository: ChatAdapterRepository,
    private val bus: ActionBus,
) {

    @Scheduled(cron = "0 0 0/1 * * *")
    fun execute() {
        LOG.debug("Hourly job started")
        val hourOfDay = getHourAt(MADRID)
        val chats = chatAdapterRepository.getAlerts(hourOfDay)
        LOG.debug("Found {} chats to alert", chats.size)
        chats.forEach { bus.dispatch(ForecastMessage(chat = it, hourOfDay = hourOfDay)) }
        LOG.debug("Hourly job finished")
    }

    private fun getHourAt(zoneId: ZoneId) = ZonedDateTime.now(zoneId).hour

    private companion object {
        private val LOG = LoggerFactory.getLogger(HourlyJob::class.java)

        val MADRID: ZoneId = ZoneId.of("Europe/Madrid")
    }
}
