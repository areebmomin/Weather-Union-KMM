package location_data_screen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.areeb.weatherunion.data.locality_data.model.LocalityData
import com.areeb.weatherunion.logic.models.WeatherData

@Composable
fun WeatherDetailsSection(
    modifier: Modifier = Modifier,
    weatherData: WeatherData,
    isLoading: Boolean,
    locality: LocalityData,
) {
    Column(
        modifier = modifier.verticalScroll(rememberScrollState()),
    ) {
        Text("Locality Name: ${locality.localityName}")
        Text("Temperature: ${weatherData.temperature.temperature}${weatherData.temperature.unit}")
        Text("Humidity: ${weatherData.humidity.humidity}${weatherData.humidity.unit}")
        Text("Wind Speed: ${weatherData.windSpeed.windSpeed}${weatherData.windSpeed.unit}")
        Text("Wind Direction: ${weatherData.windDirection.windDirection}")
        Text("Rain Intensity: ${weatherData.rainIntensity.rainIntensity}${weatherData.rainIntensity.unit}")
        Text("Total Rain: ${weatherData.rainAccumulation.rainAccumulation}${weatherData.rainAccumulation.unit}")
    }
}
