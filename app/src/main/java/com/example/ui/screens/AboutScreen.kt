package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.localization.AppStrings
import com.example.ui.theme.BlueBoxBg
import com.example.ui.theme.BlueBoxBorder
import com.example.ui.theme.BluePrimary
import com.example.ui.theme.ColorLost
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

@Composable
fun AboutScreen(
    strings: AppStrings,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(14.dp)
            .testTag("about_screen"),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        // App Description Blue Box with Logo
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(containerColor = BlueBoxBg),
            border = BorderStroke(1.5.dp, BlueBoxBorder),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // App Logo
                Image(
                    painter = painterResource(id = R.drawable.app_logo),
                    contentDescription = "Oddsly Predictions Logo",
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .testTag("about_app_logo")
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = strings.aboutTitle,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = BluePrimary
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = strings.aboutDesc1,
                    fontSize = 13.sp,
                    lineHeight = 18.sp,
                    color = TextSecondary
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = strings.aboutDesc2,
                    fontSize = 13.sp,
                    lineHeight = 18.sp,
                    color = TextSecondary
                )
            }
        }

        // Disclaimer Blue Box
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(containerColor = BlueBoxBg),
            border = BorderStroke(1.5.dp, BlueBoxBorder),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(14.dp)
            ) {
                Text(
                    text = strings.aboutDisclaimerTitle,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = ColorLost
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = strings.aboutDisclaimerBody,
                    fontSize = 12.sp,
                    lineHeight = 17.sp,
                    color = TextSecondary
                )
            }
        }
    }
}
