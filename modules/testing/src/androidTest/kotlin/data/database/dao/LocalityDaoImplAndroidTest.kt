package data.database.dao

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import app.cash.sqldelight.db.SqlDriver
import com.areeb.weatherunion.data.WeatherUnionDatabase
import com.areeb.weatherunion.data.database.AndroidDatabaseDriverFactory
import com.areeb.weatherunion.data.database.dao.LocalityDaoImpl
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LocalityDaoImplAndroidTest {

    private lateinit var driver: SqlDriver
    private lateinit var database: WeatherUnionDatabase
    private lateinit var localityDao: LocalityDaoImpl

    @Before
    fun setup() {
        val context: Context = ApplicationProvider.getApplicationContext()
        driver = AndroidDatabaseDriverFactory(context).createDriver()
        database = WeatherUnionDatabase(driver)
        localityDao = LocalityDaoImpl(database.localityQueries)
    }

    @After
    fun tearDown() {
        driver.close()
    }

    @Test
    fun testInsertAndGetLocalities() = runBlocking {
        localityDao.insertLocalities()

        val localities = localityDao.getLocalityList()
        assert(localities.isNotEmpty())
    }

    @Test
    fun testGetLocalitiesMap() = runBlocking {
        localityDao.insertLocalities()

        val localityMap = localityDao.getLocalitiesMap()
        assert(localityMap.isNotEmpty())
    }
}
