package com.areeb.weatherunion.core.util

import kotlin.math.pow
import kotlin.math.roundToInt

fun Double.round(decimals: Int): Double {
    val factor = 10.0.pow(decimals)
    return (this * factor).roundToInt() / factor
}

fun convertMeterPerSecToKmPerHour(speedInMetersPerSec: Double): Double {
    return speedInMetersPerSec * 3.6
}

fun convertDegreeToDirection(degree: Double): String {
    return when (degree) {
        in 0.0..22.5, in 337.5..360.0 -> "N"
        in 22.5..67.5 -> "NE"
        in 67.5..112.5 -> "E"
        in 112.5..157.5 -> "SE"
        in 157.5..202.5 -> "S"
        in 202.5..247.5 -> "SW"
        in 247.5..292.5 -> "W"
        in 292.5..337.5 -> "NW"
        else -> "Unknown"
    }
}
