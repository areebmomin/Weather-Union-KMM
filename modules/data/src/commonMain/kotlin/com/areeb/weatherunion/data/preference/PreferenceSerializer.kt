package com.areeb.weatherunion.data.preference

import androidx.datastore.core.okio.OkioSerializer
import com.areeb.weatherunion.data.LastSelectedLocality
import okio.BufferedSink
import okio.BufferedSource
import okio.IOException

object PreferenceSerializer : OkioSerializer<LastSelectedLocality> {
    override val defaultValue: LastSelectedLocality
        get() = LastSelectedLocality()

    override suspend fun readFrom(source: BufferedSource): LastSelectedLocality {
        try {
            return LastSelectedLocality.ADAPTER.decode(source)
        } catch (exception: IOException) {
            throw Exception(exception.message ?: "Serialization Exception")
        }
    }

    override suspend fun writeTo(t: LastSelectedLocality, sink: BufferedSink) {
        sink.write(t.encode())
    }
}
