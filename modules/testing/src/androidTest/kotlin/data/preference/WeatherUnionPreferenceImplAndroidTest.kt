package data.preference

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.areeb.weatherunion.data.LastSelectedLocality
import com.areeb.weatherunion.data.locality_data.model.LocalityData
import com.areeb.weatherunion.data.preference.AndroidDataStorePlatformValueGetter
import com.areeb.weatherunion.data.preference.ProtoDataStoreFactoryImpl
import com.areeb.weatherunion.data.preference.WeatherUnionPreferenceImpl
import com.areeb.weatherunion.data.preference.WeatherUnionPreferences
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class WeatherUnionPreferenceImplAndroidTest {

    private lateinit var weatherUnionPreferences: WeatherUnionPreferences
    private lateinit var dataStore: DataStore<LastSelectedLocality>

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val platformGetter = AndroidDataStorePlatformValueGetter(context)
        val protoDataStoreFactory = ProtoDataStoreFactoryImpl(platformGetter)
        weatherUnionPreferences = WeatherUnionPreferenceImpl(protoDataStoreFactory)

        dataStore = protoDataStoreFactory.dataStore
    }

    @Test
    fun testUpdateAndGetLastSelectedLocality() = runBlocking {
        val localityData = LocalityData(
            localityId = "123",
            cityName = "Sample City",
            localityName = "Sample Locality",
            latitude = 12.34,
            longitude = 56.78,
            deviceType = 1,
        )

        weatherUnionPreferences.updateLastSelectedLocality(localityData)

        val retrievedLocality = weatherUnionPreferences.getLastSelectedLocality().first()

        assertEquals("123", retrievedLocality?.localityId)
        assertEquals("Sample City", retrievedLocality?.cityName)
    }
}
