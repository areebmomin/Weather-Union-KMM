package home_screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
    selectedItemDisplayName: String,
    onDismissRequest: () -> Unit,
    itemDisplayName: LocalityData.() -> String,
    onItemClicked: (LocalityData) -> Unit,
) {
    val maxHeight = getScreenHeight().value * 0.7

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,
        modifier = Modifier.heightIn(max = maxHeight.dp),
        tonalElevation = 16.dp,
        containerColor = Color(0xFF1F1D47),
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                title,
                modifier = Modifier.padding(start = 16.dp, bottom = 12.dp, top = 8.dp),
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 32.dp)
                    .weight(1f),
                contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 40.dp),
            ) {
                items(localityList.size) { index ->
                    val locality = localityList[index]
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                onItemClicked(locality)
                            }
                            .padding(vertical = 8.dp, horizontal = 8.dp),
                    ) {
                        Text(
                            text = locality.itemDisplayName(),
                            fontWeight = FontWeight.Normal,
                            fontSize = 17.sp,
                            color = Color(0xB3EBEBF5),
                        )
                        Spacer(modifier = Modifier.weight(1F))
                        if (locality.itemDisplayName() == selectedItemDisplayName) {
                            Icon(
                                imageVector = Icons.Filled.Done,
                                contentDescription = null,
                                tint = Color(0x66FFFFFF),
                            )
                        }
                    }
                }
            }
        }
    }
}
