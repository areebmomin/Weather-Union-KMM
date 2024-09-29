package home_screen.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.areeb.weatherunion.logic.models.WeatherDataRainIntensity
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import utils.Colors
import weatherunionkmm.composeapp.generated.resources.Res
import weatherunionkmm.composeapp.generated.resources.ic_rain_intensity
import weatherunionkmm.composeapp.generated.resources.rain_intensity

@Composable
fun RainIntensityTile(modifier: Modifier = Modifier, rainIntensityData: WeatherDataRainIntensity) {
    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center,
    ) {
        Card(
            modifier = modifier
                .padding(start = 16.dp, end = 8.dp)
                .fillMaxWidth()
                .align(Alignment.Center),
            backgroundColor = Colors.WEATHER_DATA_CARD_BACKGROUND,
            border = BorderStroke(width = 1.dp, color = Colors.WEATHER_DATA_CARD_BORDER),
            elevation = 0.2.dp,
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                modifier = Modifier.padding(12.dp).fillMaxWidth(),
            ) {
                Row(
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Image(
                        painter = painterResource(resource = Res.drawable.ic_rain_intensity),
                        contentDescription = stringResource(Res.string.rain_intensity),
                        modifier = Modifier.padding(end = 8.dp).size(16.dp),
                        alignment = Alignment.CenterStart,
                    )
                    Text(
                        stringResource(Res.string.rain_intensity),
                        fontSize = 13.sp,
                        color = Colors.WEATHER_DATA_TITLE_TEXT,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
                AnimatedContent(
                    targetState = rainIntensityData,
                    transitionSpec = {
                        fadeIn() togetherWith fadeOut() using SizeTransform()
                    }
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        Text(
                            it.rainIntensity,
                            modifier = Modifier.alignByBaseline(),
                            fontSize = 28.sp,
                            lineHeight = 30.sp,
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            maxLines = 1,
                        )
                        Text(
                            it.unit,
                            modifier = Modifier.alignByBaseline(),
                            fontSize = 13.sp,
                            lineHeight = 30.sp,
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            maxLines = 1,
                        )
                    }
                }
            }
        }
    }
}
