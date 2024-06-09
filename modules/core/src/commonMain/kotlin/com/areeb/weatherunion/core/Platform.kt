package com.areeb.weatherunion.core

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
