package location_data_screen.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.areeb.weatherunion.data.locality_data.model.LocalityData
import com.areeb.weatherunion.logic.models.WeatherData
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import utils.Colors
import weatherunionkmm.composeapp.generated.resources.Res
import weatherunionkmm.composeapp.generated.resources.humidity
import weatherunionkmm.composeapp.generated.resources.ic_humidity
import weatherunionkmm.composeapp.generated.resources.ic_rain_accumulation
import weatherunionkmm.composeapp.generated.resources.ic_rain_intensity
import weatherunionkmm.composeapp.generated.resources.ic_wind_direction
import weatherunionkmm.composeapp.generated.resources.ic_wind_direction_arrow
import weatherunionkmm.composeapp.generated.resources.ic_wind_speed
import weatherunionkmm.composeapp.generated.resources.rain_intensity
import weatherunionkmm.composeapp.generated.resources.total_rainfall
import weatherunionkmm.composeapp.generated.resources.wind_direction
import weatherunionkmm.composeapp.generated.resources.wind_speed

@Composable
fun WeatherDetailsSection(
    modifier: Modifier = Modifier,
    weatherData: WeatherData,
    isLoading: Boolean,
    locality: LocalityData,
) {
    Box(
        modifier = Modifier
            .background(
                Brush.linearGradient(
                    listOf(
                        Colors.BACKGROUND_GRADIENT_1,
                        Colors.BACKGROUND_GRADIENT_2,
                    ),
                    tileMode = TileMode.Clamp,
                ),
            )
            .fillMaxSize(),
    ) {
        Column(
            modifier = modifier.verticalScroll(rememberScrollState()),
        ) {
            AnimatedContent(
                targetState = locality.localityName,
                transitionSpec = {
                    fadeIn() togetherWith fadeOut() using SizeTransform()
                }
            ) {
                Row(
                    modifier = Modifier.padding(start = 8.dp, top = 12.dp, end = 8.dp),
                    verticalAlignment = Alignment.Bottom,
                ) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = locality.localityName,
                        tint = Color.White,
                        modifier = Modifier.size(22.dp),
                    )
                    Text(
                        it,
                        modifier = Modifier.padding(start = 4.dp),
                        fontSize = 15.sp,
                        color = Color.White,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }

            AnimatedContent(
                targetState = weatherData.temperature,
                transitionSpec = {
                    fadeIn() togetherWith fadeOut() using SizeTransform()
                }
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp, horizontal = 16.dp),
                ) {
                    Text(
                        it.temperature,
                        modifier = Modifier.alignByBaseline(),
                        fontSize = 34.sp,
                        lineHeight = 34.sp,
                        color = Color.White,
                    )
                    Text(
                        it.unit,
                        modifier = Modifier.alignByBaseline(),
                        fontSize = 16.sp,
                        lineHeight = 34.sp,
                        color = Color.White,
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
            ) {
                Spacer(modifier = Modifier.weight(1f))
                Column(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .border(
                            border = BorderStroke(2.dp, SolidColor(Colors.WEATHER_DATA_CARD_BORDER)),
                            shape = RoundedCornerShape(8.dp),
                        )
                        .weight(2f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(top = 8.dp, bottom = 4.dp),
                    ) {
                        Image(
                            painter = painterResource(resource = Res.drawable.ic_humidity),
                            contentDescription = stringResource(Res.string.humidity),
                            modifier = Modifier.padding(end = 8.dp).size(12.dp),
                            alignment = Alignment.CenterStart,
                        )
                        Text(
                            stringResource(Res.string.humidity),
                            color = Color(0x99EBEBF5),
                            fontSize = 13.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
                    }
                    AnimatedContent(
                        targetState = weatherData.humidity,
                        transitionSpec = {
                            fadeIn() togetherWith fadeOut() using SizeTransform()
                        }
                    ) {
                        Text(
                            "${it.humidity}${it.unit}",
                            modifier = Modifier.padding(top = 4.dp, bottom = 8.dp),
                            color = Color.White,
                        )
                    }
                }
                Spacer(modifier = Modifier.weight(1f))
            }

            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
            ) {
                Column(
                    modifier = Modifier
                        .padding(start = 16.dp, end = 8.dp)
                        .border(
                            border = BorderStroke(2.dp, SolidColor(Colors.WEATHER_DATA_CARD_BORDER)),
                            shape = RoundedCornerShape(8.dp),
                        )
                        .weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(top = 8.dp, bottom = 4.dp),
                    ) {
                        Image(
                            painter = painterResource(resource = Res.drawable.ic_wind_speed),
                            contentDescription = stringResource(Res.string.wind_speed),
                            modifier = Modifier.padding(end = 8.dp).size(12.dp),
                            alignment = Alignment.CenterStart,
                        )
                        Text(
                            stringResource(Res.string.wind_speed),
                            color = Color(0x99EBEBF5),
                            fontSize = 13.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
                    }
                    AnimatedContent(
                        targetState = weatherData.windSpeed,
                        transitionSpec = {
                            fadeIn() togetherWith fadeOut() using SizeTransform()
                        }
                    ) {
                        Text(
                            "${it.windSpeed}${it.unit}",
                            modifier = Modifier.padding(top = 4.dp, bottom = 8.dp),
                            color = Color.White,
                        )
                    }
                }
                Column(
                    modifier = Modifier
                        .padding(start = 8.dp, end = 16.dp)
                        .border(
                            border = BorderStroke(2.dp, SolidColor(Colors.WEATHER_DATA_CARD_BORDER)),
                            shape = RoundedCornerShape(8.dp),
                        )
                        .weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(top = 8.dp, bottom = 4.dp),
                    ) {
                        Image(
                            painter = painterResource(resource = Res.drawable.ic_wind_direction),
                            contentDescription = stringResource(Res.string.wind_direction),
                            modifier = Modifier.padding(end = 8.dp).size(12.dp),
                            alignment = Alignment.CenterStart,
                        )
                        Text(
                            stringResource(Res.string.wind_direction),
                            color = Color(0x99EBEBF5),
                            fontSize = 13.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
                    }

                    AnimatedContent(
                        targetState = weatherData.windDirection,
                        transitionSpec = {
                            fadeIn() togetherWith fadeOut() using SizeTransform()
                        }
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth().padding(top = 4.dp, bottom = 8.dp),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            it.windDirectionDegree?.let { degree ->
                                Image(
                                    painter = painterResource(resource = Res.drawable.ic_wind_direction_arrow),
                                    contentDescription = stringResource(Res.string.wind_direction),
                                    modifier = Modifier
                                        .padding(end = 8.dp)
                                        .size(16.dp)
                                        .rotate(degree),
                                    alignment = Alignment.CenterStart,
                                )
                            }
                            Text(
                                it.windDirection.ifEmpty { it.error },
                                color = Color.White,
                            )
                        }
                    }
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
            ) {
                Column(
                    modifier = Modifier
                        .padding(start = 16.dp, end = 8.dp)
                        .border(
                            border = BorderStroke(2.dp, SolidColor(Colors.WEATHER_DATA_CARD_BORDER)),
                            shape = RoundedCornerShape(8.dp),
                        )
                        .weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(top = 8.dp, bottom = 4.dp),
                    ) {
                        Image(
                            painter = painterResource(resource = Res.drawable.ic_rain_intensity),
                            contentDescription = stringResource(Res.string.rain_intensity),
                            modifier = Modifier.padding(end = 8.dp).size(12.dp),
                            alignment = Alignment.CenterStart,
                        )
                        Text(
                            stringResource(Res.string.rain_intensity),
                            color = Color(0x99EBEBF5),
                            fontSize = 13.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
                    }
                    AnimatedContent(
                        targetState = weatherData.rainIntensity,
                        transitionSpec = {
                            fadeIn() togetherWith fadeOut() using SizeTransform()
                        }
                    ) {
                        Text(
                            "${it.rainIntensity}${it.unit}",
                            modifier = Modifier.padding(top = 4.dp, bottom = 8.dp),
                            color = Color.White,
                        )
                    }
                }
                Column(
                    modifier = Modifier
                        .padding(start = 8.dp, end = 16.dp)
                        .border(
                            border = BorderStroke(2.dp, SolidColor(Colors.WEATHER_DATA_CARD_BORDER)),
                            shape = RoundedCornerShape(8.dp),
                        )
                        .weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(top = 8.dp, bottom = 4.dp),
                    ) {
                        Image(
                            painter = painterResource(resource = Res.drawable.ic_rain_accumulation),
                            contentDescription = stringResource(Res.string.total_rainfall),
                            modifier = Modifier.padding(end = 8.dp).size(12.dp),
                            alignment = Alignment.CenterStart,
                        )
                        Text(
                            stringResource(Res.string.total_rainfall),
                            color = Color(0x99EBEBF5),
                            fontSize = 13.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
                    }
                    AnimatedContent(
                        targetState = weatherData.rainAccumulation,
                        transitionSpec = {
                            fadeIn() togetherWith fadeOut() using SizeTransform()
                        }
                    ) {
                        Text(
                            "${it.rainAccumulation}${it.unit}",
                            modifier = Modifier.padding(top = 4.dp, bottom = 8.dp),
                            color = Color.White,
                        )
                    }
                }
            }
        }
    }
}
