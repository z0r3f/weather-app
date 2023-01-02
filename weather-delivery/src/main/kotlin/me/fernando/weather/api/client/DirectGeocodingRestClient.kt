package me.fernando.weather.api.client

import io.micronaut.cache.annotation.Cacheable
import io.micronaut.http.HttpHeaders
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Header
import io.micronaut.http.client.annotation.Client
import me.fernando.weather.api.dto.GeographicalCoordinateDto

// TODO Add cache option
@Client("\${weather.geo.url}")
@Header(name = HttpHeaders.USER_AGENT, value = "Micronaut HTTP Client")
@Header(name = HttpHeaders.ACCEPT, value = "application/vnd.github.v3+json, application/json")

interface DirectGeocodingRestClient {

    @Cacheable(value = ["search-by-name"])
    @Get("?appid=\${weather.api-key}&q={address}&limit=\${weather.geo.results-limit}")
    fun getCoordinatesByLocationName(address: String): List<GeographicalCoordinateDto>
}
