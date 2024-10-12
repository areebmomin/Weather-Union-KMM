import com.areeb.weatherunion.data.locality_data.model.LocalityData
import com.areeb.weatherunion.data.preference.DataStorePlatformValuesGetter
import com.areeb.weatherunion.data.preference.ProtoDataStoreFactory
import com.areeb.weatherunion.data.preference.ProtoDataStoreFactoryImpl
import com.areeb.weatherunion.data.preference.WeatherUnionPreferenceImpl
import com.areeb.weatherunion.data.preference.WeatherUnionPreferences
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okio.FileSystem
import okio.Path.Companion.toPath
import platform.Foundation.NSFileManager
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class WeatherUnionPreferenceImplIosTest {

    private lateinit var weatherUnionPreferences: WeatherUnionPreferences

    @BeforeTest
    fun setup() {
        val fileSystem = FileSystem.SYSTEM
        val basePath = NSFileManager.defaultManager.currentDirectoryPath.toPath()
        val protoDataStoreFactory = ProtoDataStoreFactoryImpl(
            object :
                DataStorePlatformValuesGetter {
                override fun getFileSystem(): FileSystem = fileSystem
                override fun getProducePath(): () -> okio.Path = {
                    basePath.resolve(ProtoDataStoreFactory.DATA_STORE_FILE_NAME)
                }
            },
        )

        weatherUnionPreferences = WeatherUnionPreferenceImpl(protoDataStoreFactory)
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
