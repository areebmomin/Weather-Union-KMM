package com.areeb.weatherunion.data.preference

import androidx.datastore.core.DataStore
import com.areeb.weatherunion.data.AndroidPlatformContextProvider
import com.areeb.weatherunion.data.PreferenceData
import okio.FileSystem
import okio.Path.Companion.toPath

actual fun getDataStore(): DataStore<PreferenceData> {
    val content = requireNotNull(AndroidPlatformContextProvider.context)
    val producePath = { content.filesDir.resolve(DATA_STORE_FILE_NAME).absolutePath.toPath() }

    return createDataStore(fileSystem = FileSystem.SYSTEM, producePath = producePath)
}
