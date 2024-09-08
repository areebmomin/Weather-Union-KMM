package home_screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.areeb.weatherunion.data.locality_data.model.LocalityData
import utils.getScreenHeight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun showLocalitiesBottomSheet(
    title: String,
    sheetState: SheetState,
    localityList: List<LocalityData>,
    onDismissRequest: () -> Unit,
    itemDisplayName: LocalityData.() -> String,
    onItemClicked: (LocalityData) -> Unit,
) {
    val maxHeight = getScreenHeight().value * 0.9

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,
        modifier = Modifier.heightIn(max = maxHeight.dp),
        tonalElevation = 4.dp,
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                title,
                modifier = Modifier.padding(start = 16.dp, bottom = 8.dp, top = 8.dp),
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 32.dp)
                    .weight(1f),
                contentPadding = PaddingValues(start = 24.dp, end = 24.dp, bottom = 40.dp),
            ) {
                items(localityList.size) { index ->
                    val locality = localityList[index]
                    Text(
                        text = locality.itemDisplayName(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                            .clickable {
                                onItemClicked(locality)
                            },
                        fontWeight = FontWeight.Normal,
                        fontSize = 17.sp,
                    )
                }
            }
        }
    }
}
