package me.fernando.weather.cqrs

import io.archimedesfw.cqrs.QueryMessage
import me.fernando.chat.domain.FavoriteLocation
import me.fernando.weather.domain.WeatherData

data class GetForecastByFavoriteLocationMessage(val favoriteLocation: FavoriteLocation) : QueryMessage<WeatherData>
