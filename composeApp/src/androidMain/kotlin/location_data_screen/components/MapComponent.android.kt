package location_data_screen.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import utils.getScreenHeight

@Composable
actual fun MapComponent() {
    val percent70Height = getScreenHeight().value * 0.7
    Box(modifier = Modifier
        .height(percent70Height.dp)
        .fillMaxWidth()
        .border(2.dp, Color.Blue)) {
        Text(text = "I am from Platform UI code")
    }
}
