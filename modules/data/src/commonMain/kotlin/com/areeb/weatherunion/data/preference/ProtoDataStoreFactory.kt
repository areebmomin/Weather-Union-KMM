package com.areeb.weatherunion.data.preference

import androidx.datastore.core.DataStore
import com.areeb.weatherunion.data.PreferenceData

interface ProtoDataStoreFactory {
    companion object {
        internal const val DATA_STORE_FILE_NAME = "weather_union.preferences_pb"
    }

    val dataStore: DataStore<PreferenceData>
}
