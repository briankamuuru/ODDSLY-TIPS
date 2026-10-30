package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.PredictionEntity
import com.example.localization.AppStrings
import com.example.ui.components.DateGroupedPredictionsList
import com.example.ui.theme.BlueBoxBg
import com.example.ui.theme.BlueBoxBorder
import com.example.ui.theme.BluePrimary
import com.example.ui.theme.PureWhite
import com.example.ui.theme.TextPrimary

@Composable
fun VipHistoryScreen(
    selectedCategory: String,
    onSelectCategory: (String) -> Unit,
    predictions: List<PredictionEntity>,
    strings: AppStrings,
    modifier: Modifier = Modifier
) {
    val categories = listOf(
        "VIP_2_PLUS_HISTORY" to strings.histTab2Plus,
        "VIP_3_PLUS_HISTORY" to strings.histTab3Plus,
        "VIP_5_PLUS_HISTORY" to strings.histTab5Plus,
        "VIP_SPECIAL_HISTORY" to strings.histTabSpecial
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .testTag("vip_history_screen")
    ) {
        // Category Pills as Blue Boxes
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
                .padding(horizontal = 14.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            categories.forEach { (catKey, catLabel) ->
                val isSelected = selectedCategory == catKey
                Card(
                    onClick = { onSelectCategory(catKey) },
                    modifier = Modifier.testTag("history_tab_${catKey}"),
                    shape = RoundedCornerShape(10.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = if (isSelected) BluePrimary else BlueBoxBg
                    ),
                    border = BorderStroke(
                        1.5.dp,
                        BlueBoxBorder
                    )
                ) {
                    Text(
                        text = catLabel,
                        fontSize = 13.sp,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                        color = if (isSelected) PureWhite else TextPrimary,
                        modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp)
                    )
                }
            }
        }

        // Date-grouped predictions with orange day/date headers and blue accumulated odds box
        DateGroupedPredictionsList(
            predictions = predictions,
            strings = strings,
            emptyMessage = strings.emptyHistory,
            modifier = Modifier.fillMaxSize(),
            testTagPrefix = "vip_history"
        )
    }
}
