package com.areeb.weatherunion.core.di

import com.areeb.weatherunion.core.coroutines.AppCoroutineDispatchers
import com.areeb.weatherunion.core.coroutines.ApplicationCoroutineScope
import com.areeb.weatherunion.core.coroutines.CoroutineDispatchers
import com.areeb.weatherunion.core.logger.KermitLogger
import com.areeb.weatherunion.core.logger.Logger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import me.tatarka.inject.annotations.Provides
import me.tatarka.inject.annotations.Scope

@Scope
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER)
annotation class ApplicationScope

interface CoreComponent {

    val applicationCoroutineScope: ApplicationCoroutineScope

    @ApplicationScope
    @Provides
    fun applicationCoroutineScope(): ApplicationCoroutineScope =
        CoroutineScope(AppCoroutineDispatchers.coroutineDispatchers.mainImmediate + SupervisorJob())

    @ApplicationScope
    @Provides
    fun coroutineDispatchers(): CoroutineDispatchers = AppCoroutineDispatchers.coroutineDispatchers

    @ApplicationScope
    @Provides
    fun providesLogger(): Logger = KermitLogger()
}
