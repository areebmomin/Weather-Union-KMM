package com.areeb.weatherunion.logic

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform