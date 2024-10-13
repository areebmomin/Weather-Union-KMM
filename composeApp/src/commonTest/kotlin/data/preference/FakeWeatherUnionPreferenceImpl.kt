package data.preference

import com.areeb.weatherunion.data.locality_data.model.LocalityData
import com.areeb.weatherunion.data.preference.WeatherUnionPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeWeatherUnionPreferenceImpl : WeatherUnionPreferences {
    private var lastSelectedLocality: LocalityData? = null

    override suspend fun updateLastSelectedLocality(locality: LocalityData) {
        lastSelectedLocality = locality
    }

    override fun getLastSelectedLocality(): Flow<LocalityData?> {
        return flow {
            emit(lastSelectedLocality)
        }
    }
}
