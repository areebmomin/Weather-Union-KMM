package data.database.dao

import app.cash.sqldelight.db.SqlDriver
import com.areeb.weatherunion.data.WeatherUnionDatabase
import com.areeb.weatherunion.data.database.IosDatabaseDriverFactory
import com.areeb.weatherunion.data.database.dao.ApiKeyDaoImpl
import kotlinx.coroutines.runBlocking
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class ApiKeyDaoImplIosTest {

    private lateinit var driver: SqlDriver
    private lateinit var database: WeatherUnionDatabase
    private lateinit var apiKeyDao: ApiKeyDaoImpl

    @BeforeTest
    fun setup() {
        driver = IosDatabaseDriverFactory().createDriver()
        database = WeatherUnionDatabase(driver)
        apiKeyDao = ApiKeyDaoImpl(database.apiKeyQueries)
    }

    @AfterTest
    fun tearDown() {
        driver.close()
    }

    @Test
    fun testInsertAndRetrieveApiKey() = runBlocking {
        val testApiKey = "test_api_key_ios"

        apiKeyDao.updateWeatherUnionApiKey(testApiKey)

        val retrievedApiKey = apiKeyDao.getWeatherUnionApiKey()

        assertEquals(testApiKey, retrievedApiKey)
    }
}
