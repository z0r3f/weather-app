package io.github.z0r3f.weather.infrastructure.rest.forecast.dto

open class CloudsDtoMother {
    open fun of(all: Int? = null) = CloudsDto(all = all ?: 0)

}
