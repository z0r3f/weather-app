package io.github.z0r3f.weather.infrastructure.rest.forecast.dto

open class AirDataDtoMother {
    open fun of(
        coordinate: CoordinateDto? = null,
        list: List<AirDataListDto>? = null,
    ) = AirDataDto(
        coordinate = coordinate ?: CoordinateDtoMother().of(),
        list = list ?: listOf(AirDataListDtoMother().of()),
    )
}

open class AirDataListDtoMother {
    open fun of(
        dt: Long? = null,
        main: AirMainDto? = null,
        components: ComponentsDto? = null,
    ) = AirDataListDto(
        dt = dt ?: 1627304400,
        main = main ?: AirMainDtoMother().of(),
        components = components ?: ComponentsDtoMother().of(),
    )
}

open class AirMainDtoMother {
    open fun of(
        aqi: Int? = null,
    ) = AirMainDto(
        aqi = aqi ?: 1,
    )
}

open class ComponentsDtoMother {
    open fun of(
        co: Double? = null,
        no: Double? = null,
        no2: Double? = null,
        o3: Double? = null,
        so2: Double? = null,
        pm25: Double? = null,
        pm10: Double? = null,
        nh3: Double? = null,
    ) = ComponentsDto(
        co = co ?: 223.64,
        no = no ?: 0.0,
        no2 = no2 ?: 0.49,
        o3 = o3 ?: 56.51,
        so2 = so2 ?: 0.07,
        pm25 = pm25 ?: 0.57,
        pm10 = pm10 ?: 0.92,
        nh3 = nh3 ?: 0.43,
    )
}
