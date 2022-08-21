package me.fernando.weather.api.dto

open class ForecastDtoMother {
    open fun of(
        dt: Long? = null,
        main: MainDto? = null,
        weather: List<WeatherDto>? = null,
        clouds: CloudsDto? = null,
        wind: WindDto? = null,
        rain: RainDto? = null,
        snow: SnowDto? = null,
        visibility: Int? = null,
        probabilityOfPrecipitation: Double? = null,
        dateTime: String? = null,
    ) = ForecastDto(
        dt = dt ?: 1644958800,
        main = main ?: MainDtoMother().of(),
        weather = weather ?: listOf(WeatherDtoMother().of()),
        clouds = clouds ?: CloudsDtoMother().of(),
        wind = wind ?: WindDtoMother().of(),
        rain = rain ?: RainDtoMother().of(),
        snow = snow ?: SnowDtoMother().of(),
        visibility = visibility ?: 10000,
        probabilityOfPrecipitation = probabilityOfPrecipitation ?: 0.32,
        dateTime = dateTime ?: "2022-02-15 21:00:00",
    )
}
