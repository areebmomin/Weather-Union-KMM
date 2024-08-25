package home_screen

import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.areeb.weatherunion.logic.home_screen.HomeScreenViewModel

@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel,
    onNavigateToLocationDataScreen: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold {
        Button(
            onClick = onNavigateToLocationDataScreen,
        ) {
            Text("Goto Location Data Screen")
        }
    }
}
