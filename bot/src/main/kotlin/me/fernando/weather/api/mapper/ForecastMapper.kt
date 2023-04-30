package me.fernando.weather.api.mapper

import jakarta.inject.Singleton
import me.fernando.weather.api.dto.ForecastDto
import me.fernando.weather.architecture.mapper.Mapper
import me.fernando.weather.domain.Forecast
import java.time.Instant
import java.time.LocalDateTime

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
        LocalDateTime.ofInstant(Instant.ofEpochSecond(epochSecond), java.time.ZoneOffset.ofTotalSeconds(timezone ?: 0))
}
