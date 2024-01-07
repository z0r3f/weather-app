package io.github.z0r3f.weather.core.forecast.domain

data class Air(
    val airQualityIndex: Int? = null, // Air Quality Index. Possible values: 1, 2, 3, 4, 5. Where 1 = Good, 2 = Fair, 3 = Moderate, 4 = Poor, 5 = Very Poor
    val carbonMonoxide: Double? = null, //  Сoncentration of CO (Carbon monoxide), μg/m3
    val nitrogenMonoxide: Double? = null, // Сoncentration of NO (Nitrogen monoxide), μg/m3
    val nitrogenDioxide: Double? = null, // Сoncentration of NO2 (Nitrogen dioxide), μg/m3
    val ozone: Double? = null, // Сoncentration of O3 (Ozone), μg/m3
    val sulphurDioxide: Double? = null, // Сoncentration of SO2 (Sulphur dioxide), μg/m3
    val fineParticlesMatter: Double? = null, // Сoncentration of PM2.5 (Fine particles matter), μg/m3
    val coarseParticulateMatter: Double? = null, // Сoncentration of PM10 (Coarse particulate matter), μg/m3
    val ammonia: Double? = null, // Сoncentration of NH3 (Ammonia), μg/m3
)
