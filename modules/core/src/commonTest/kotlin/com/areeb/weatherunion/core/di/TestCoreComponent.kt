package com.areeb.weatherunion.core.di

import com.areeb.weatherunion.core.fakes.TestLogger
import com.areeb.weatherunion.core.logger.CoreLogger
import me.tatarka.inject.annotations.Provides

interface TestCoreComponent {
    val TestLogger.binds: CoreLogger
        @ApplicationScope @Provides get() = this
}
