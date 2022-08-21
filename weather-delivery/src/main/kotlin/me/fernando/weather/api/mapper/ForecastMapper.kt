package me.fernando.weather.api.mapper

import jakarta.inject.Singleton
import me.fernando.weather.api.dto.ForecastDto
import me.fernando.weather.domain.Forecast
import java.time.Instant

@Singleton
class ForecastMapper(
    private val weatherMapper: WeatherMapper
): Mapper<Forecast, ForecastDto> {
    override fun toDto(entity: Forecast): ForecastDto {
        TODO("Not yet implemented")
    }

    override fun toEntity(dto: ForecastDto): Forecast {
        return Forecast(
            timeDataForecasted = Instant.ofEpochSecond(dto.dt),
            temperature = dto.main.temp,
            feelsLike = dto.main.feelsLike,
            temperatureMin = dto.main.tempMin,
            temperatureMax = dto.main.tempMax,
            pressure = dto.main.pressure,
            humidity = dto.main.humidity,
            weather = dto.weather.map { weatherMapper.toEntity(it) },
            windSpeed = dto.wind.speed,
            visibility = dto.visibility,
            probabilityOfPrecipitation = dto.probabilityOfPrecipitation,
        )
    }
}
