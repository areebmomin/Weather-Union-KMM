package com.areeb.weatherunion.data.preference

import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.core.okio.OkioStorage
import com.areeb.weatherunion.data.PreferenceData
import okio.FileSystem
import okio.Path

internal const val DATA_STORE_FILE_NAME = "weather_union.preferences_pb"

expect fun getDataStore(): DataStore<PreferenceData>

fun createDataStore(
    fileSystem: FileSystem,
    producePath: () -> Path
): DataStore<PreferenceData> =
    DataStoreFactory.create(
        storage = OkioStorage(
            fileSystem = fileSystem,
            producePath = producePath,
            serializer = PreferenceSerializer,
        ),
    )
