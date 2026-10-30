package com.example.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Functions
import androidx.compose.material.icons.filled.Numbers
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.PredictionEntity
import com.example.localization.AppStrings
import com.example.ui.theme.BlueBoxBg
import com.example.ui.theme.BlueBoxBorder
import com.example.ui.theme.BluePrimary
import com.example.ui.theme.ColorOrange
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import java.util.Locale

@Composable
fun DateGroupedPredictionsList(
    predictions: List<PredictionEntity>,
    strings: AppStrings,
    emptyMessage: String,
    modifier: Modifier = Modifier,
    testTagPrefix: String = "predictions"
) {
    if (predictions.isEmpty()) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(24.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = emptyMessage,
                color = TextMuted,
                fontSize = 14.sp,
                modifier = Modifier.testTag("${testTagPrefix}_empty_text")
            )
        }
    } else {
        // Group by day date (preserving order)
        val groupedByDate = predictions.groupBy { it.matchDate }

        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .testTag("${testTagPrefix}_list"),
            contentPadding = PaddingValues(horizontal = 14.dp, vertical = 10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            groupedByDate.forEach { (matchDate, datePredictions) ->
                // 1. Day & Date Header (MANDATORY IN ORANGE)
                item(key = "header_$matchDate") {
                    DateDayHeader(matchDate = matchDate)
                }

                // 2. Predictions under that day's date
                items(
                    items = datePredictions,
                    key = { it.id }
                ) { prediction ->
                    PredictionCard(
                        prediction = prediction,
                        strings = strings,
                        showDateInHeader = false
                    )
                }

                // 3. At the end of that date odds: A Blue Box showing total accumulated odds and number of odds only
                item(key = "summary_$matchDate") {
                    DailyAccumulatedOddsBox(
                        datePredictions = datePredictions,
                        strings = strings,
                        dateKey = matchDate
                    )
                }

                // Small spacing after date group
                item(key = "spacer_$matchDate") {
                    Spacer(modifier = Modifier.height(4.dp))
                }
            }
        }
    }
}

/**
 * Date and day header displayed in ORANGE colour as required.
 */
@Composable
fun DateDayHeader(
    matchDate: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 8.dp, bottom = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.CalendarToday,
            contentDescription = null,
            tint = ColorOrange,
            modifier = Modifier.size(16.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        // Date and Day in ORANGE colour
        Text(
            text = matchDate,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = ColorOrange
        )
    }
}

/**
 * Blue box showing the total accumulated odds and number of odds only at the end of each date.
 */
@Composable
fun DailyAccumulatedOddsBox(
    datePredictions: List<PredictionEntity>,
    strings: AppStrings,
    dateKey: String,
    modifier: Modifier = Modifier
) {
    // Calculate accumulated product of odds
    val accumulatedOdds = datePredictions
        .mapNotNull { it.odds.toDoubleOrNull() }
        .fold(1.0) { acc, odd -> acc * odd }

    val formattedAccumulatedOdds = String.format(Locale.US, "%.2f", accumulatedOdds)
    val numberOfOdds = datePredictions.size

    Card(
        modifier = modifier
            .fillMaxWidth()
            .testTag("accumulated_odds_box_${dateKey.replace(" ", "_")}"),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = BlueBoxBg),
        border = BorderStroke(1.5.dp, BlueBoxBorder),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Accumulated Odds
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Functions,
                    contentDescription = null,
                    tint = BluePrimary,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Column {
                    Text(
                        text = strings.accumulatedOddsLabel,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium,
                        color = TextSecondary
                    )
                    Text(
                        text = formattedAccumulatedOdds,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = BluePrimary
                    )
                }
            }

            // Number of Odds
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Numbers,
                    contentDescription = null,
                    tint = BluePrimary,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = strings.numberOfOddsLabel,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium,
                        color = TextSecondary
                    )
                    Text(
                        text = numberOfOdds.toString(),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary
                    )
                }
            }
        }
    }
}
