package com.areeb.weatherunion.shared

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform