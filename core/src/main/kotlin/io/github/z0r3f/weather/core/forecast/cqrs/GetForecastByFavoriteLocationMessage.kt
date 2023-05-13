package io.github.z0r3f.weather.core.forecast.cqrs

import io.archimedesfw.cqrs.QueryMessage
import io.github.z0r3f.weather.core.chat.domain.FavoriteLocation
import io.github.z0r3f.weather.core.forecast.domain.WeatherData

data class GetForecastByFavoriteLocationMessage(val favoriteLocation: FavoriteLocation) : QueryMessage<WeatherData>
