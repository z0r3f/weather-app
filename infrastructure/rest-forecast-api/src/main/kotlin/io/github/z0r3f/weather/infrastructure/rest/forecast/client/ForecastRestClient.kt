package io.github.z0r3f.weather.infrastructure.rest.forecast.client

import io.github.z0r3f.weather.infrastructure.rest.forecast.dto.WeatherDataDto
import io.micronaut.cache.annotation.Cacheable
import io.micronaut.http.HttpHeaders.ACCEPT
import io.micronaut.http.HttpHeaders.USER_AGENT
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Header
import io.micronaut.http.client.annotation.Client

@Client("\${weather.data.url}")
@Header(name = USER_AGENT, value = "Micronaut HTTP Client")
@Header(name = ACCEPT, value = "application/vnd.github.v3+json, application/json")
interface ForecastRestClient {

    @Cacheable(value = ["forecast-by-coordinate"])
    @Get("/forecast?appid=\${weather.api-key}&lat={latitude}&lon={longitude}&units=metric")
    fun getForecast(latitude: Double, longitude: Double): WeatherDataDto
}
