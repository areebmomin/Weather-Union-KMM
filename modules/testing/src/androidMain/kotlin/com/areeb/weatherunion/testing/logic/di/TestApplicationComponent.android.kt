package com.areeb.weatherunion.testing.logic.di

actual fun createTestApplicationComponent(): TestApplicationComponent =
    TestApplicationComponent.create()
