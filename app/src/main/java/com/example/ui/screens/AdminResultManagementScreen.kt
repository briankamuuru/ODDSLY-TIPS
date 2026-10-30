package com.example.ui.screens

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
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.HourglassEmpty
import androidx.compose.material.icons.filled.RestartAlt
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.PredictionEntity
import com.example.localization.AppStrings
import com.example.ui.theme.BlueBoxBg
import com.example.ui.theme.BlueBoxBorder
import com.example.ui.theme.BlueBoxBorderSubtle
import com.example.ui.theme.BluePrimary
import com.example.ui.theme.CardBorder
import com.example.ui.theme.ColorLost
import com.example.ui.theme.ColorOrange
import com.example.ui.theme.ColorPending
import com.example.ui.theme.ColorWon
import com.example.ui.theme.PureWhite
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

@Composable
fun AdminResultManagementScreen(
    predictions: List<PredictionEntity>,
    onDeclareResult: (String, String) -> Unit,
    onResetData: () -> Unit,
    strings: AppStrings,
    modifier: Modifier = Modifier
) {
    var selectedFilter by remember { mutableStateOf("ALL") } // ALL, FREE, VIP
    var showResetConfirmDialog by remember { mutableStateOf(false) }

    val filteredList = when (selectedFilter) {
        "FREE" -> predictions.filter { it.category == "FREE" }
        "VIP" -> predictions.filter { it.category != "FREE" }
        else -> predictions
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .testTag("admin_result_management_screen")
    ) {
        // Notice bar explaining automated VIP movement - Blue Box
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 6.dp),
            colors = CardDefaults.cardColors(containerColor = BlueBoxBg),
            border = BorderStroke(1.5.dp, BlueBoxBorder),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(
                text = strings.adminNotice,
                fontSize = 11.sp,
                color = BluePrimary,
                lineHeight = 15.sp,
                modifier = Modifier.padding(10.dp)
            )
        }

        // Filter chips and Reset Data Button
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                FilterChip(
                    selected = selectedFilter == "ALL",
                    onClick = { selectedFilter = "ALL" },
                    label = { Text(strings.adminFilterAll, fontSize = 12.sp) },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = BluePrimary,
                        selectedLabelColor = PureWhite
                    )
                )
                FilterChip(
                    selected = selectedFilter == "FREE",
                    onClick = { selectedFilter = "FREE" },
                    label = { Text(strings.adminFilterFree, fontSize = 12.sp) },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = BluePrimary,
                        selectedLabelColor = PureWhite
                    )
                )
                FilterChip(
                    selected = selectedFilter == "VIP",
                    onClick = { selectedFilter = "VIP" },
                    label = { Text(strings.adminFilterVip, fontSize = 12.sp) },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = BluePrimary,
                        selectedLabelColor = PureWhite
                    )
                )
            }

            OutlinedButton(
                onClick = { showResetConfirmDialog = true },
                modifier = Modifier.testTag("btn_reset_data"),
                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp),
                shape = RoundedCornerShape(6.dp),
                border = BorderStroke(1.dp, BlueBoxBorder)
            ) {
                Icon(
                    imageVector = Icons.Default.RestartAlt,
                    contentDescription = null,
                    tint = BluePrimary,
                    modifier = Modifier.size(14.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = strings.resetData,
                    fontSize = 11.sp,
                    color = BluePrimary
                )
            }
        }

        if (filteredList.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(text = strings.emptyAdmin, color = TextMuted, fontSize = 14.sp)
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 14.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(vertical = 8.dp)
            ) {
                items(
                    items = filteredList,
                    key = { it.id }
                ) { prediction ->
                    AdminPredictionItem(
                        prediction = prediction,
                        strings = strings,
                        onSetResult = { newResult ->
                            onDeclareResult(prediction.id, newResult)
                        }
                    )
                }
            }
        }
    }

    if (showResetConfirmDialog) {
        AlertDialog(
            onDismissRequest = { showResetConfirmDialog = false },
            title = { Text(text = strings.resetData, fontWeight = FontWeight.Bold, color = TextPrimary) },
            text = { Text(text = strings.resetDataConfirm, color = TextSecondary) },
            confirmButton = {
                TextButton(
                    onClick = {
                        onResetData()
                        showResetConfirmDialog = false
                    }
                ) {
                    Text(text = strings.confirm, color = ColorLost, fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = {
                TextButton(onClick = { showResetConfirmDialog = false }) {
                    Text(text = strings.cancel, color = TextSecondary)
                }
            },
            containerColor = PureWhite
        )
    }
}

@Composable
private fun AdminPredictionItem(
    prediction: PredictionEntity,
    strings: AppStrings,
    onSetResult: (String) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = PureWhite),
        border = BorderStroke(1.dp, BlueBoxBorderSubtle),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            // League and Date in Orange
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = prediction.league,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = ColorOrange
                )
                Text(
                    text = "${prediction.matchDate} • ${prediction.kickoffTime}",
                    fontSize = 11.sp,
                    color = ColorOrange
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "${prediction.homeTeam}  vs  ${prediction.awayTeam}",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = TextPrimary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(2.dp))

            Text(
                text = "${prediction.predictionText}  •  ${strings.oddsLabel}: ${prediction.odds}",
                fontSize = 12.sp,
                color = TextSecondary
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Action buttons for Admin to declare outcome
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Pending button
                OutlinedButton(
                    onClick = { onSetResult("PENDING") },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(6.dp),
                    contentPadding = PaddingValues(vertical = 4.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = if (prediction.resultState == "PENDING") CardBorder else PureWhite
                    ),
                    border = BorderStroke(
                        1.dp,
                        if (prediction.resultState == "PENDING") ColorPending else CardBorder
                    )
                ) {
                    Icon(
                        imageVector = Icons.Default.HourglassEmpty,
                        contentDescription = null,
                        tint = ColorPending,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = strings.markPending,
                        fontSize = 11.sp,
                        fontWeight = if (prediction.resultState == "PENDING") FontWeight.Bold else FontWeight.Normal,
                        color = ColorPending
                    )
                }

                // Won button
                OutlinedButton(
                    onClick = { onSetResult("WON") },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(6.dp),
                    contentPadding = PaddingValues(vertical = 4.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = if (prediction.resultState == "WON") ColorWon.copy(alpha = 0.12f) else PureWhite
                    ),
                    border = BorderStroke(
                        1.dp,
                        if (prediction.resultState == "WON") ColorWon else CardBorder
                    )
                ) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                        tint = ColorWon,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = strings.markWon,
                        fontSize = 11.sp,
                        fontWeight = if (prediction.resultState == "WON") FontWeight.Bold else FontWeight.Normal,
                        color = ColorWon
                    )
                }

                // Lost button
                OutlinedButton(
                    onClick = { onSetResult("LOST") },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(6.dp),
                    contentPadding = PaddingValues(vertical = 4.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = if (prediction.resultState == "LOST") ColorLost.copy(alpha = 0.12f) else PureWhite
                    ),
                    border = BorderStroke(
                        1.dp,
                        if (prediction.resultState == "LOST") ColorLost else CardBorder
                    )
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = null,
                        tint = ColorLost,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = strings.markLost,
                        fontSize = 11.sp,
                        fontWeight = if (prediction.resultState == "LOST") FontWeight.Bold else FontWeight.Normal,
                        color = ColorLost
                    )
                }
            }
        }
    }
}
