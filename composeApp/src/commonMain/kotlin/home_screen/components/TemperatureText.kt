package home_screen.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.areeb.weatherunion.logic.models.WeatherDataTemperature

@Composable
fun TemperatureText(modifier: Modifier = Modifier, temperatureData: WeatherDataTemperature) {
    AnimatedContent(
        targetState = temperatureData,
        transitionSpec = {
            fadeIn() togetherWith fadeOut() using SizeTransform()
        }
    ) {
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            Text(
                it.temperature,
                textAlign = TextAlign.Center,
                fontSize = 96.sp,
                lineHeight = 96.sp,
                color = Color.White,
                modifier = modifier.alignByBaseline(),
                fontWeight = FontWeight.W200,
            )
            Text(
                it.unit,
                textAlign = TextAlign.Center,
                fontSize = 28.sp,
                lineHeight = 96.sp,
                color = Color.White,
                modifier = modifier.alignByBaseline(),
                fontWeight = FontWeight.W200,
            )
        }
    }
}
