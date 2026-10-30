package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.localization.AppStrings
import com.example.ui.theme.BlueBoxBg
import com.example.ui.theme.BlueBoxBorder
import com.example.ui.theme.BluePrimary
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

@Composable
fun PrivacyPolicyScreen(
    strings: AppStrings,
    modifier: Modifier = Modifier
) {
    val sections = listOf(
        strings.ppSection1Title to strings.ppSection1Body,
        strings.ppSection2Title to strings.ppSection2Body,
        strings.ppSection3Title to strings.ppSection3Body,
        strings.ppSection4Title to strings.ppSection4Body,
        strings.ppSection5Title to strings.ppSection5Body,
        strings.ppSection6Title to strings.ppSection6Body,
        strings.ppSection7Title to strings.ppSection7Body,
        strings.ppSection8Title to strings.ppSection8Body
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(14.dp)
            .testTag("privacy_policy_screen"),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        sections.forEach { (title, body) ->
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
                        text = title,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = BluePrimary
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = body,
                        fontSize = 13.sp,
                        lineHeight = 18.sp,
                        color = TextSecondary
                    )
                }
            }
        }
    }
}
