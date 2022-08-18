package me.fernando.weather.api.client

import io.micronaut.http.HttpHeaders.ACCEPT
import io.micronaut.http.HttpHeaders.USER_AGENT
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Header
import io.micronaut.http.client.annotation.Client
import me.fernando.weather.api.dto.WeatherDataDto

@Client("\${weather.data.url}")
@Header(name = USER_AGENT, value = "Micronaut HTTP Client")
@Header(name = ACCEPT, value = "application/vnd.github.v3+json, application/json")
interface ForecastRestClient {

    @Get("/forecast?appid=\${weather.api-key}&lat={latitude}&lon={longitude}&units=metric")
    fun getForecast(latitude: Double, longitude: Double): WeatherDataDto
}
