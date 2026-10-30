package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.SportsScore
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material.icons.filled.Whatshot
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.localization.AppStrings
import com.example.ui.theme.BlueBoxBg
import com.example.ui.theme.BlueBoxBorder
import com.example.ui.theme.BluePrimary
import com.example.ui.theme.BlueSecondary
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

@Composable
fun VipTipsScreen(
    strings: AppStrings,
    onSelectVipSubCategory: (String) -> Unit,
    onSelectHistoryVip: () -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("vip_tips_screen"),
        contentPadding = PaddingValues(horizontal = 14.dp, vertical = 14.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        // Row 1: 2+ Tips VIP and 3+ Tips VIP (Blue boxes)
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                VipCategoryCard(
                    title = strings.vip2PlusTitle,
                    description = strings.vip2PlusDesc,
                    icon = Icons.Default.TrendingUp,
                    modifier = Modifier
                        .weight(1f)
                        .testTag("vip_card_2_plus"),
                    onClick = { onSelectVipSubCategory("VIP_2_PLUS") }
                )
                VipCategoryCard(
                    title = strings.vip3PlusTitle,
                    description = strings.vip3PlusDesc,
                    icon = Icons.Default.SportsScore,
                    modifier = Modifier
                        .weight(1f)
                        .testTag("vip_card_3_plus"),
                    onClick = { onSelectVipSubCategory("VIP_3_PLUS") }
                )
            }
        }

        // Row 2: 5+ Tips VIP and Special VIP (Blue boxes)
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                VipCategoryCard(
                    title = strings.vip5PlusTitle,
                    description = strings.vip5PlusDesc,
                    icon = Icons.Default.Whatshot,
                    modifier = Modifier
                        .weight(1f)
                        .testTag("vip_card_5_plus"),
                    onClick = { onSelectVipSubCategory("VIP_5_PLUS") }
                )
                VipCategoryCard(
                    title = strings.vipSpecialTitle,
                    description = strings.vipSpecialDesc,
                    icon = Icons.Default.Star,
                    modifier = Modifier
                        .weight(1f)
                        .testTag("vip_card_special"),
                    onClick = { onSelectVipSubCategory("VIP_SPECIAL") }
                )
            }
        }

        // Row 3: History VIP spanning both columns (Blue box)
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("vip_card_history")
                    .clickable { onSelectHistoryVip() },
                shape = RoundedCornerShape(10.dp),
                colors = CardDefaults.cardColors(containerColor = BlueBoxBg),
                border = BorderStroke(1.5.dp, BlueBoxBorder),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 14.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.History,
                        contentDescription = "History icon",
                        tint = BluePrimary,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(14.dp))
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = strings.vipHistoryTitle,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = TextPrimary
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = strings.vipHistoryDesc,
                            fontSize = 12.sp,
                            color = TextSecondary,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = "Open History",
                        tint = BlueSecondary,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun VipCategoryCard(
    title: String,
    description: String,
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .height(115.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = BlueBoxBg),
        border = BorderStroke(1.5.dp, BlueBoxBorder),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = BluePrimary,
                    modifier = Modifier.size(20.dp)
                )
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = null,
                    tint = BlueSecondary,
                    modifier = Modifier.size(14.dp)
                )
            }

            Column {
                Text(
                    text = title,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = description,
                    fontSize = 11.sp,
                    color = TextSecondary,
                    maxLines = 2,
                    lineHeight = 14.sp,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}
