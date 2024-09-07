package home_screen.components

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
import com.areeb.weatherunion.logic.models.WeatherDataHumidity
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import weatherunionkmm.composeapp.generated.resources.Res
import weatherunionkmm.composeapp.generated.resources.humidity
import weatherunionkmm.composeapp.generated.resources.ic_humidity

@Composable
fun HumidityTile(modifier: Modifier = Modifier, humidityData: WeatherDataHumidity) {
    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center,
    ) {
        Card(
            modifier = modifier
                .padding(horizontal = 8.dp)
                .fillMaxWidth()
                .align(Alignment.Center),
            backgroundColor = Color(0xFF2B2655),
            border = BorderStroke(width = 1.dp, color = Color(0xFF454887)),
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
                        painter = painterResource(resource = Res.drawable.ic_humidity),
                        contentDescription = stringResource(Res.string.humidity),
                        modifier = Modifier.padding(end = 8.dp).size(16.dp),
                        alignment = Alignment.CenterStart,
                    )
                    Text(
                        stringResource(Res.string.humidity),
                        fontSize = 13.sp,
                        color = Color(0x99EBEBF5),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Text(
                        humidityData.humidity,
                        modifier = Modifier.alignByBaseline(),
                        fontSize = 28.sp,
                        lineHeight = 30.sp,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        maxLines = 1,
                    )
                    Text(
                        humidityData.unit,
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
