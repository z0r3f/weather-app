package io.github.z0r3f.weather.core.forecast.domain

open class AirMother {
    open fun of(
        airQualityIndex: Int? = 1,
        carbonMonoxide: Double? = 223.64,
        nitrogenMonoxide: Double? = 0.0,
        nitrogenDioxide: Double? = 0.49,
        ozone: Double? = 56.51,
        sulphurDioxide: Double? = 0.07,
        fineParticlesMatter: Double? = 0.57,
        coarseParticulateMatter: Double? = 0.92,
        ammonia: Double? = 0.43,
    ) = Air(
        airQualityIndex = airQualityIndex,
        carbonMonoxide = carbonMonoxide,
        nitrogenMonoxide = nitrogenMonoxide,
        nitrogenDioxide = nitrogenDioxide,
        ozone = ozone,
        sulphurDioxide = sulphurDioxide,
        fineParticlesMatter = fineParticlesMatter,
        coarseParticulateMatter = coarseParticulateMatter,
        ammonia = ammonia,
    )
}
