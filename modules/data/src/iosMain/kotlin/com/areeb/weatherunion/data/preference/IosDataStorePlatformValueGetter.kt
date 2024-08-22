package com.areeb.weatherunion.data.preference

import kotlinx.cinterop.ExperimentalForeignApi
import me.tatarka.inject.annotations.Inject
import okio.FileSystem
import okio.Path
import okio.Path.Companion.toPath
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSURL
import platform.Foundation.NSUserDomainMask

@Inject
class IosDataStorePlatformValueGetter : DataStorePlatformValuesGetter {
    override fun getFileSystem(): FileSystem = FileSystem.SYSTEM

    @OptIn(ExperimentalForeignApi::class)
    override fun getProducePath(): () -> Path = {
        val documentDirectory: NSURL? = NSFileManager.defaultManager.URLForDirectory(
            directory = NSDocumentDirectory,
            inDomain = NSUserDomainMask,
            appropriateForURL = null,
            create = false,
            error = null,
        )
        val path =
            requireNotNull(documentDirectory).path + "/${ProtoDataStoreFactory.DATA_STORE_FILE_NAME}"

        path.toPath()
    }
}
