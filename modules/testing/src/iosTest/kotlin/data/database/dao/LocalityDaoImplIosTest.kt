package data.database.dao

import app.cash.sqldelight.db.SqlDriver
import com.areeb.weatherunion.data.WeatherUnionDatabase
import com.areeb.weatherunion.data.database.IosDatabaseDriverFactory
import com.areeb.weatherunion.data.database.dao.LocalityDaoImpl
import kotlinx.coroutines.runBlocking
import kotlin.experimental.ExperimentalNativeApi
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test

class LocalityDaoImplIosTest {

    private lateinit var driver: SqlDriver
    private lateinit var database: WeatherUnionDatabase
    private lateinit var localityDao: LocalityDaoImpl

    @BeforeTest
    fun setup() {
        driver = IosDatabaseDriverFactory().createDriver()
        database = WeatherUnionDatabase(driver)
        localityDao = LocalityDaoImpl(database.localityQueries)
    }

    @AfterTest
    fun tearDown() {
        driver.close()
    }

    @OptIn(ExperimentalNativeApi::class)
    @Test
    fun testInsertAndGetLocalities() = runBlocking {
        localityDao.insertLocalities()

        val localities = localityDao.getLocalityList()
        assert(localities.isNotEmpty())
    }

    @OptIn(ExperimentalNativeApi::class)
    @Test
    fun testGetLocalitiesMap() = runBlocking {
        localityDao.insertLocalities()

        val localityMap = localityDao.getLocalitiesMap()
        assert(localityMap.isNotEmpty())
    }
}
