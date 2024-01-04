package io.github.z0r3f.weather.infrastructure.rest.forecast.mapper

import io.github.z0r3f.weather.architecture.mapper.Mapper
import io.github.z0r3f.weather.core.forecast.domain.Forecast
import io.github.z0r3f.weather.infrastructure.rest.forecast.dto.ForecastDto
import jakarta.inject.Singleton
import java.time.Instant.ofEpochSecond
import java.time.LocalDateTime.ofInstant
import java.time.ZoneOffset.ofTotalSeconds

@Singleton
class ForecastMapper(
    private val weatherMapper: WeatherMapper,
) : Mapper<Forecast, ForecastDto> {
    override fun toDto(model: Forecast): ForecastDto {
        TODO("Not yet implemented")
    }

    override fun toModel(dto: ForecastDto): Forecast {
        return Forecast(
            timeDataForecasted = getLocalDateTime(dto.dt, dto.timezone),
            temperature = dto.main.temp,
            feelsLike = dto.main.feelsLike,
            temperatureMin = dto.main.tempMin,
            temperatureMax = dto.main.tempMax,
            pressure = dto.main.pressure,
            humidity = dto.main.humidity,
            weather = dto.weather.map { weatherMapper.toModel(it) },
            windSpeed = dto.wind.speed,
            visibility = dto.visibility,
            probabilityOfPrecipitation = dto.probabilityOfPrecipitation,
        )
    }

    private fun getLocalDateTime(epochSecond: Long, timezone: Int?) =
        ofInstant(ofEpochSecond(epochSecond), ofTotalSeconds(timezone ?: 0))
}
