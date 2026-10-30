package com.example.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckBox
import androidx.compose.material.icons.outlined.CheckBoxOutlineBlank
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.PredictionEntity
import com.example.localization.AppStrings
import com.example.ui.theme.BlueBoxBorderSubtle
import com.example.ui.theme.ColorLost
import com.example.ui.theme.ColorOrange
import com.example.ui.theme.ColorPending
import com.example.ui.theme.ColorWon
import com.example.ui.theme.PureWhite
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

@Composable
fun PredictionCard(
    prediction: PredictionEntity,
    strings: AppStrings,
    showDateInHeader: Boolean = false,
    modifier: Modifier = Modifier
) {
    val isWon = prediction.resultState == "WON"
    val isLost = prediction.resultState == "LOST"
    val isPending = prediction.resultState == "PENDING"

    // Odds color rule: Won = green, Lost = red, Pending = dark neutral
    val oddsColor = when {
        isWon -> ColorWon
        isLost -> ColorLost
        else -> TextPrimary
    }

    // Visual indicator checkbox color: Won = green, Lost = red, Pending = muted grey
    val indicatorColor = when {
        isWon -> ColorWon
        isLost -> ColorLost
        else -> ColorPending
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .testTag("prediction_card_${prediction.id}"),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = PureWhite),
        border = BorderStroke(1.dp, BlueBoxBorderSubtle),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 12.dp)
        ) {
            // Header: League (MANDATORY IN ORANGE) and Date/Kickoff Time (Date/Day IN ORANGE)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // League name must be in orange colour
                Text(
                    text = prediction.league,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = ColorOrange,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f, fill = false)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (showDateInHeader) {
                        // Date/Day in orange
                        Text(
                            text = "${prediction.matchDate} • ",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Medium,
                            color = ColorOrange,
                            maxLines = 1
                        )
                    }
                    Text(
                        text = prediction.kickoffTime,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium,
                        color = TextMuted,
                        maxLines = 1
                    )
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            // Teams: Home vs Away
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${prediction.homeTeam}  ${strings.vs}  ${prediction.awayTeam}",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Footer: Prediction on Left, Odds and Result Checkbox on Right
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Prediction text
                Text(
                    text = prediction.predictionText,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = TextSecondary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f, fill = false)
                )

                Spacer(modifier = Modifier.width(12.dp))

                // Odds and Visual Result Checkbox Indicator
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "${strings.oddsLabel}: ",
                        fontSize = 12.sp,
                        color = TextMuted
                    )
                    Text(
                        text = prediction.odds,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = oddsColor,
                        modifier = Modifier.testTag("prediction_odds_${prediction.id}")
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    // Visual checkbox indicator:
                    // Pending = empty unticked box
                    // Won / Lost = ticked box
                    // NEVER displays the word "Won", "Lost", or "Pending"
                    if (isPending) {
                        Icon(
                            imageVector = Icons.Outlined.CheckBoxOutlineBlank,
                            contentDescription = "Pending status indicator",
                            tint = indicatorColor,
                            modifier = Modifier
                                .size(18.dp)
                                .testTag("result_indicator_pending_${prediction.id}")
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Filled.CheckBox,
                            contentDescription = "Completed status indicator",
                            tint = indicatorColor,
                            modifier = Modifier
                                .size(18.dp)
                                .testTag("result_indicator_completed_${prediction.id}")
                        )
                    }
                }
            }
        }
    }
}
