package home_screen.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DeviceDescriptionText(modifier: Modifier = Modifier, deviceDescription: String) {
    Text(
        "*$deviceDescription",
        modifier = modifier.padding(horizontal = 16.dp),
        color = Color(0x99EBEBF5),
        fontSize = 11.sp,
    )
}
