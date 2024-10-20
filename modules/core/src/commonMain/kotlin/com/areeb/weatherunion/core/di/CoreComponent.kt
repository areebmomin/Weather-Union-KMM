package com.areeb.weatherunion.core.di

import com.areeb.weatherunion.core.coroutines.AppCoroutineDispatchers
import com.areeb.weatherunion.core.coroutines.CoroutineDispatchers
import com.areeb.weatherunion.core.logger.CoreLogger
import com.areeb.weatherunion.core.logger.KermitLogger
import com.areeb.weatherunion.core.network.http_client.HttpClientFactory
import com.areeb.weatherunion.core.network.http_client.WeatherUnionHttpClientFactory
import me.tatarka.inject.annotations.Provides
import me.tatarka.inject.annotations.Scope

@Scope
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER)
annotation class ApplicationScope

interface CoreComponent {

    val coroutineDispatchers: CoroutineDispatchers

    @ApplicationScope
    @Provides
    fun coroutineDispatchers(): CoroutineDispatchers = AppCoroutineDispatchers.coroutineDispatchers

    val KermitLogger.binds: CoreLogger
        @ApplicationScope @Provides get() = this

    val WeatherUnionHttpClientFactory.binds: HttpClientFactory
        @ApplicationScope @Provides get() = this
}
