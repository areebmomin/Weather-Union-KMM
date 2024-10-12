package data.database.dao

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import app.cash.sqldelight.db.SqlDriver
import com.areeb.weatherunion.data.WeatherUnionDatabase
import com.areeb.weatherunion.data.database.AndroidDatabaseDriverFactory
import com.areeb.weatherunion.data.database.dao.ApiKeyDaoImpl
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ApiKeyDaoImplAndroidTest {

    private lateinit var driver: SqlDriver
    private lateinit var database: WeatherUnionDatabase
    private lateinit var apiKeyDao: ApiKeyDaoImpl

    @Before
    fun setup() {
        val context: Context = ApplicationProvider.getApplicationContext()
        driver = AndroidDatabaseDriverFactory(context).createDriver()
        database = WeatherUnionDatabase(driver)
        apiKeyDao = ApiKeyDaoImpl(database.apiKeyQueries)
    }

    @After
    fun tearDown() {
        driver.close()
    }

    @Test
    fun testInsertAndRetrieveApiKey() = runBlocking {
        val testApiKey = "test_api_key"

        apiKeyDao.updateWeatherUnionApiKey(testApiKey)

        val retrievedApiKey = apiKeyDao.getWeatherUnionApiKey()

        assertEquals(testApiKey, retrievedApiKey)
    }
}
