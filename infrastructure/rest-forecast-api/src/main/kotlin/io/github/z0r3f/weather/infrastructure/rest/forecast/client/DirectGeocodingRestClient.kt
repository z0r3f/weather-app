package io.github.z0r3f.weather.infrastructure.rest.forecast.client

import io.github.z0r3f.weather.infrastructure.rest.forecast.dto.GeographicalCoordinateDto
import io.micronaut.cache.annotation.Cacheable
import io.micronaut.http.HttpHeaders
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Header
import io.micronaut.http.client.annotation.Client

@Client("\${weather.geo.url}")
@Header(name = HttpHeaders.USER_AGENT, value = "Micronaut HTTP Client")
@Header(name = HttpHeaders.ACCEPT, value = "application/vnd.github.v3+json, application/json")

interface DirectGeocodingRestClient {

    @Cacheable(value = ["search-by-name"])
    @Get("?appid=\${weather.api-key}&q={address}&limit=\${weather.geo.results-limit}")
    fun getCoordinatesByLocationName(address: String): List<GeographicalCoordinateDto>
}
