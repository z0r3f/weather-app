package io.github.z0r3f.weather.core.util

// https://en.wikipedia.org/wiki/Air_quality_index
object AirIndex {
    private val carbonMonoxide: Map<ClosedRange<Double>, Int> = mapOf(
        0.0..50.0 to 1,
        50.0..100.0 to 2,
        100.0..150.0 to 3,
        150.0..200.0 to 4,
        200.0..Double.MAX_VALUE to 5
    )

    private val nitrogenMonoxide: Map<ClosedRange<Double>, Int> = mapOf(
        0.0..40.0 to 1,
        40.0..80.0 to 2,
        80.0..180.0 to 3,
        180.0..280.0 to 4,
        280.0..Double.MAX_VALUE to 5
    )

    private val nitrogenDioxide: Map<ClosedRange<Double>, Int> = mapOf(
        0.0..40.0 to 1,
        40.0..90.0 to 2,
        90.0..120.0 to 3,
        120.0..230.0 to 4,
        230.0..Double.MAX_VALUE to 5
    )

    private val ozone: Map<ClosedRange<Double>, Int> = mapOf(
        0.0..80.0 to 1,
        80.0..120.0 to 2,
        120.0..180.0 to 3,
        180.0..240.0 to 4,
        240.0..Double.MAX_VALUE to 5
    )

    private val sulphurDioxide: Map<ClosedRange<Double>, Int> = mapOf(
        0.0..100.0 to 1,
        100.0..200.0 to 2,
        200.0..350.0 to 3,
        350.0..500.0 to 4,
        500.0..Double.MAX_VALUE to 5
    )

    private val fineParticlesMatter: Map<ClosedRange<Double>, Int> = mapOf(
        0.0..30.0 to 1,
        30.0..60.0 to 2,
        60.0..90.0 to 3,
        90.0..120.0 to 4,
        120.0..Double.MAX_VALUE to 5
    )

    private val coarseParticulateMatter: Map<ClosedRange<Double>, Int> = mapOf(
        0.0..50.0 to 1,
        50.0..100.0 to 2,
        100.0..250.0 to 3,
        250.0..350.0 to 4,
        350.0..Double.MAX_VALUE to 5
    )

    private val ammonia: Map<ClosedRange<Double>, Int> = mapOf(
        0.0..200.0 to 1,
        200.0..400.0 to 2,
        400.0..800.0 to 3,
        800.0..1200.0 to 4,
        1200.0..Double.MAX_VALUE to 5
    )

    private fun getIndex(value: Double?, ranges: Map<ClosedRange<Double>, Int>): Int? {
        if (value == null) return null
        return ranges.entries.find { (range, _) -> value in range }?.value
    }

    fun getCarbonMonoxideIndex(value: Double?): Int? {
        return getIndex(value, carbonMonoxide)
    }

    fun getNitrogenMonoxideIndex(value: Double?): Int? {
        return getIndex(value, nitrogenMonoxide)
    }

    fun getNitrogenDioxideIndex(value: Double?): Int? {
        return getIndex(value, nitrogenDioxide)
    }

    fun getOzoneIndex(value: Double?): Int? {
        return getIndex(value, ozone)
    }

    fun getSulphurDioxideIndex(value: Double?): Int? {
        return getIndex(value, sulphurDioxide)
    }

    fun getFineParticlesMatterIndex(value: Double?): Int? {
        return getIndex(value, fineParticlesMatter)
    }

    fun getCoarseParticulateMatterIndex(value: Double?): Int? {
        return getIndex(value, coarseParticulateMatter)
    }

    fun getAmmoniaIndex(value: Double?): Int? {
        return getIndex(value, ammonia)
    }
}
