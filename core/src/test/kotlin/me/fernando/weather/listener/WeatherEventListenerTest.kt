package me.fernando.weather.listener

import io.archimedesfw.cqrs.ActionBus
import io.micronaut.context.event.ApplicationEventPublisher
import me.fernando.chat.domain.Chat
import me.fernando.chat.domain.FavoriteLocation
import me.fernando.telegram.domain.callback.BotCallback
import me.fernando.telegram.domain.callback.BotCallbackType
import me.fernando.telegram.event.MessageEvent
import me.fernando.weather.cqrs.GetFavoriteLocationsMessage
import me.fernando.weather.cqrs.GetForecastByCityNameMessage
import me.fernando.weather.cqrs.GetForecastByFavoriteLocationMessage
import me.fernando.weather.domain.LocationMother
import me.fernando.weather.domain.WeatherDataMother
import me.fernando.weather.event.RequestForecastEvent
import me.fernando.weather.service.ForecastOverviewService
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions
import org.mockito.kotlin.whenever

internal class WeatherEventListenerTest {

    private val bus: ActionBus = mock()
    private val forecastOverviewService: ForecastOverviewService = mock()
    private val newMessageEventPublisher: ApplicationEventPublisher<MessageEvent> = mock()

    private lateinit var sut: WeatherEventListener

    @BeforeEach
    internal fun setUp() {
        sut = WeatherEventListener(bus, forecastOverviewService, newMessageEventPublisher)
    }

    @AfterEach
    internal fun tearDown() {
        verifyNoMoreInteractions(bus, forecastOverviewService, newMessageEventPublisher)
    }

    @Test
    internal fun `request forecast event with city not is favorite`() {
        val request = REQUEST_SAO_PAULO
        val weatherData = WeatherDataMother().of()
        whenever(bus.dispatch(GetForecastByCityNameMessage(CITY_SAO_PAULO))).thenReturn(weatherData)
        whenever(bus.dispatch(GetFavoriteLocationsMessage(CHAT))).thenReturn(listOf(FAVORITE_TOKYO))
        whenever(forecastOverviewService.generateOverviewMessage(weatherData)).thenReturn(RESPONSE_SAO_PAOLO_FORMATTED)

        sut.onRequestForecastEvent(request)

        verify(bus).dispatch(GetForecastByCityNameMessage(CITY_SAO_PAULO))
        verify(bus).dispatch(GetFavoriteLocationsMessage(CHAT))
        verify(forecastOverviewService).generateOverviewMessage(weatherData)
        verify(newMessageEventPublisher).publishEventAsync(
            MessageEvent(
                chat = CHAT,
                message = RESPONSE_SAO_PAOLO_FORMATTED,
                botCallbacks = listOf(CALLBACK_SAO_PAULO_NO_FAVORITE)
            )
        )
    }

    @Test
    internal fun `request forecast event without city name`() {
        val request = REQUEST_FORECAST
        val weatherData = WeatherDataMother().of(location = LocationMother().of(name = CITY_TOKYO))
        whenever(bus.dispatch(GetFavoriteLocationsMessage(CHAT))).thenReturn(listOf(FAVORITE_TOKYO))
        whenever(bus.dispatch(GetForecastByFavoriteLocationMessage(FAVORITE_TOKYO))).thenReturn(weatherData)
        whenever(forecastOverviewService.generateOverviewMessage(weatherData)).thenReturn(RESPONSE_TOKYO_FORMATTED)

        sut.onRequestForecastEvent(request)

        verify(bus).dispatch(GetFavoriteLocationsMessage(CHAT))
        verify(bus).dispatch(GetForecastByFavoriteLocationMessage(FAVORITE_TOKYO))
        verify(forecastOverviewService).generateOverviewMessage(weatherData)
        verify(newMessageEventPublisher).publishEventAsync(
            MessageEvent(
                chat = CHAT,
                message = RESPONSE_TOKYO_FORMATTED,
                botCallbacks = listOf(CALLBACK_TOKYO_FAVORITE)
            )
        )
    }

    @Test
    internal fun `request forecast event with city is favorite`() {
        val request = REQUEST_TOKYO
        val weatherData = WeatherDataMother().of(location = LocationMother().of(name = CITY_TOKYO))
        whenever(bus.dispatch(GetForecastByCityNameMessage(CITY_TOKYO))).thenReturn(weatherData)
        whenever(bus.dispatch(GetFavoriteLocationsMessage(CHAT))).thenReturn(listOf(FAVORITE_TOKYO))
        whenever(forecastOverviewService.generateOverviewMessage(weatherData)).thenReturn(RESPONSE_SAO_PAOLO_FORMATTED)

        sut.onRequestForecastEvent(request)

        verify(bus).dispatch(GetForecastByCityNameMessage(CITY_TOKYO))
        verify(bus).dispatch(GetFavoriteLocationsMessage(CHAT))
        verify(forecastOverviewService).generateOverviewMessage(weatherData)
        verify(newMessageEventPublisher).publishEventAsync(
            MessageEvent(
                chat = CHAT,
                message = RESPONSE_SAO_PAOLO_FORMATTED,
                botCallbacks = listOf(CALLBACK_TOKYO_FAVORITE)
            )
        )
    }

    @Test
    internal fun `request scheduled forecast without city name and with hour`() {
        val request = REQUEST_SCHEDULED_FORECAST
        val weatherData = WeatherDataMother().of(location = LocationMother().of(name = CITY_TOKYO))
        whenever(bus.dispatch(GetFavoriteLocationsMessage(CHAT))).thenReturn(listOf(FAVORITE_TOKYO))
        whenever(bus.dispatch(GetForecastByFavoriteLocationMessage(FAVORITE_TOKYO))).thenReturn(weatherData)
        whenever(forecastOverviewService.generateOverviewMessage(weatherData)).thenReturn(RESPONSE_TOKYO_FORMATTED)

        sut.onRequestForecastEvent(request)

        verify(bus).dispatch(GetFavoriteLocationsMessage(CHAT))
        verify(bus).dispatch(GetForecastByFavoriteLocationMessage(FAVORITE_TOKYO))
        verify(forecastOverviewService).generateOverviewMessage(weatherData)
        verify(newMessageEventPublisher).publishEventAsync(
            MessageEvent(
                chat = CHAT,
                message = RESPONSE_TOKYO_FORMATTED,
                botCallbacks = listOf(CALLBACK_TOKYO_FAVORITE, CALLBACK_TOKYO_FAVORITE_DELETE_ALERT)
            )
        )
    }

    private companion object {
        val CHAT = mock<Chat>()
        const val CITY_SAO_PAULO = "São Paulo"
        const val CITY_TOKYO = "Tokyo"
        const val RESPONSE_SAO_PAOLO_FORMATTED = "☀️$CITY_SAO_PAULO"
        const val RESPONSE_TOKYO_FORMATTED = "🌧 $CITY_TOKYO"
        val REQUEST_SAO_PAULO = RequestForecastEvent(chat = CHAT, cityName = CITY_SAO_PAULO)
        val FAVORITE_TOKYO = FavoriteLocation(chat = CHAT, name = "Tokyo", latitude = -35.6895, longitude = 139.6917, country = "Japan")
        val CALLBACK_SAO_PAULO_NO_FAVORITE = BotCallback(type = BotCallbackType.ADD, data = "${BotCallbackType.ADD.name}:$CITY_SAO_PAULO")
        val REQUEST_FORECAST = RequestForecastEvent(chat = CHAT)
        val CALLBACK_TOKYO_FAVORITE = BotCallback(type = BotCallbackType.DELETE, data = "${BotCallbackType.DELETE.name}:$CITY_TOKYO")
        val REQUEST_TOKYO = RequestForecastEvent(chat = CHAT, cityName = CITY_TOKYO)
        const val TOKIO_ALERT_AT_SEVEN = 7
        val REQUEST_SCHEDULED_FORECAST = RequestForecastEvent(chat = CHAT, hourOfDay = TOKIO_ALERT_AT_SEVEN)
        val CALLBACK_TOKYO_FAVORITE_DELETE_ALERT = BotCallback(type = BotCallbackType.DELETE_ALERT, data = "${BotCallbackType.DELETE_ALERT.name}:$TOKIO_ALERT_AT_SEVEN")
    }
}
