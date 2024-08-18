package com.areeb.weatherunion.core.di

import com.areeb.weatherunion.core.coroutines.AppCoroutineDispatchers
import com.areeb.weatherunion.core.coroutines.ApplicationCoroutineScope
import com.areeb.weatherunion.core.coroutines.CoroutineDispatchers
import com.areeb.weatherunion.core.logger.CoreLogger
import com.areeb.weatherunion.core.logger.KermitLogger
import com.areeb.weatherunion.core.network.CustomKtorLogger
import com.areeb.weatherunion.core.network.http_client.HttpClientFactory
import com.areeb.weatherunion.core.network.http_client.WeatherUnionHttpClientFactory
import io.ktor.client.plugins.logging.Logger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import me.tatarka.inject.annotations.Provides
import me.tatarka.inject.annotations.Scope

@Scope
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER)
annotation class ApplicationScope

interface CoreComponent {

    @ApplicationScope
    @Provides
    fun applicationCoroutineScope(): ApplicationCoroutineScope =
        CoroutineScope(AppCoroutineDispatchers.coroutineDispatchers.mainImmediate + SupervisorJob())

    @ApplicationScope
    @Provides
    fun coroutineDispatchers(): CoroutineDispatchers = AppCoroutineDispatchers.coroutineDispatchers

    val KermitLogger.binds: CoreLogger
        @ApplicationScope @Provides get() = this

    val CustomKtorLogger.binds: Logger
        @ApplicationScope @Provides get() = this

    val WeatherUnionHttpClientFactory.binds: HttpClientFactory
        @ApplicationScope @Provides get() = this
}
