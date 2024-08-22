package com.areeb.weatherunion.data.preference

import okio.FileSystem
import okio.Path

interface DataStorePlatformValuesGetter {
    fun getFileSystem(): FileSystem
    fun getProducePath(): () -> Path
}
