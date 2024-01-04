package io.github.z0r3f.weather.infrastructure.rest.forecast.dto

open class CurrentDataDtoMother {
    open fun of(
        coordinate: CoordinateDto? = null,
        weather: WeatherDto? = null,
        main: MainDto? = null,
        visibility: Int? = null,
        wind: WindDto? = null,
        rain: RainDto? = null,
        clouds: CloudsDto? = null,
        dt: Long? = null,
        sys: SysDto? = null,
        timezone: Int? = null,
        id: Long? = null,
        name: String? = null,
    ) = CurrentDataDto(
        coordinate = coordinate ?: CoordinateDtoMother().of(),
        weather = weather ?: WeatherDtoMother().of(),
        main = main ?: MainDtoMother().of(),
        visibility = visibility ?: 10000,
        wind = wind ?: WindDtoMother().of(),
        rain = rain ?: RainDtoMother().of(),
        clouds = clouds ?: CloudsDtoMother().of(),
        dt = dt ?: 1627304400,
        sys = sys ?: SysDtoMother().of(),
        timezone = timezone ?: 3600,
        id = id ?: 2643743,
        name = name ?: "Madrid",
    )
}
