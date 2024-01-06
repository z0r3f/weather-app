package io.github.z0r3f.weather.infrastructure.rest.forecast.client

import io.github.z0r3f.weather.infrastructure.rest.forecast.dto.AirDataDto
import io.micronaut.cache.annotation.Cacheable
import io.micronaut.http.HttpHeaders
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Header
import io.micronaut.http.client.annotation.Client

@Client("\${weather.data.url}")
@Header(name = HttpHeaders.USER_AGENT, value = "Micronaut HTTP Client")
@Header(name = HttpHeaders.ACCEPT, value = "application/vnd.github.v3+json, application/json")
interface AirRestClient {

    @Cacheable(value = ["air-by-coordinate"])
    @Get("/air_pollution?appid=\${weather.api-key}&lat={latitude}&lon={longitude}&units=metric")
    fun getAir(latitude: Double, longitude: Double): AirDataDto
}
