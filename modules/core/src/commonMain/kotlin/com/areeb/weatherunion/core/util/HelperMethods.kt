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
        in 0.0..22.5, in 337.5..360.0 -> "North"
        in 22.5..67.5 -> "North-East"
        in 67.5..112.5 -> "East"
        in 112.5..157.5 -> "South-East"
        in 157.5..202.5 -> "South"
        in 202.5..247.5 -> "South-West"
        in 247.5..292.5 -> "West"
        in 292.5..337.5 -> "North-West"
        else -> "Unknown"
    }
}
