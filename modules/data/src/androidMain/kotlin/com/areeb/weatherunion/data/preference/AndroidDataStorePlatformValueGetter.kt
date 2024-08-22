package com.areeb.weatherunion.data.preference

import android.content.Context
import me.tatarka.inject.annotations.Inject
import okio.FileSystem
import okio.Path
import okio.Path.Companion.toPath

@Inject
class AndroidDataStorePlatformValueGetter(private val context: Context) :
    DataStorePlatformValuesGetter {

    override fun getFileSystem(): FileSystem = FileSystem.SYSTEM

    override fun getProducePath(): () -> Path =
        { context.filesDir.resolve(ProtoDataStoreFactory.DATA_STORE_FILE_NAME).absolutePath.toPath() }
}
