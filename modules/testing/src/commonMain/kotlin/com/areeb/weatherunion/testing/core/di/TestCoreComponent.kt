package com.areeb.weatherunion.testing.core.di

import com.areeb.weatherunion.core.di.ApplicationScope
import com.areeb.weatherunion.testing.core.logger.TestLogger
import com.areeb.weatherunion.core.logger.CoreLogger
import me.tatarka.inject.annotations.Provides

interface TestCoreComponent {
    val TestLogger.binds: CoreLogger
        @ApplicationScope @Provides get() = this
}
