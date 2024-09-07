package home_screen.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.areeb.weatherunion.logic.models.WeatherDataWindSpeed

@Composable
fun WindSpeedTile(modifier: Modifier = Modifier, windSpeedData: WeatherDataWindSpeed) {
    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center,
    ) {
        Card(
            modifier = modifier
                .padding(start = 16.dp, end = 8.dp)
                .fillMaxWidth()
                .align(Alignment.Center),
            backgroundColor = Color(0xFF2B2655),
            border = BorderStroke(width = 1.dp, color = Color(0xFF454887)),
            elevation = 0.2.dp,
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                modifier = modifier.padding(12.dp).fillMaxWidth(),
            ) {
                Text(
                    "Wind Speed",
                    fontSize = 13.sp,
                    color = Color(0x99EBEBF5),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Row(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Text(
                        windSpeedData.windSpeed,
                        modifier = Modifier.alignByBaseline(),
                        fontSize = 28.sp,
                        lineHeight = 28.sp,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        maxLines = 1,
                    )
                    Text(
                        windSpeedData.unit,
                        modifier = Modifier.alignByBaseline(),
                        fontSize = 13.sp,
                        lineHeight = 28.sp,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        maxLines = 1,
                    )
                }
            }
        }
    }
}