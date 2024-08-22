package com.areeb.weatherunion.data.preference

import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.core.okio.OkioStorage
import com.areeb.weatherunion.data.PreferenceData
import me.tatarka.inject.annotations.Inject

@Inject
class ProtoDataStoreFactoryImpl(valueGetter: DataStorePlatformValuesGetter) :
    ProtoDataStoreFactory {

    override val dataStore: DataStore<PreferenceData> = DataStoreFactory.create(
        storage = OkioStorage(
            fileSystem = valueGetter.getFileSystem(),
            producePath = valueGetter.getProducePath(),
            serializer = PreferenceSerializer,
        ),
    )
}
