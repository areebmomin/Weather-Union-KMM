package com.areeb.weatherunion.data

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
