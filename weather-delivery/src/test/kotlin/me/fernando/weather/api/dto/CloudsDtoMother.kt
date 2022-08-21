package me.fernando.weather.api.dto

open class CloudsDtoMother {
    open fun of(all: Int? = null) = CloudsDto(all = all ?: 0)

}
