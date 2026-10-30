package com.example.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
import com.example.ui.theme.PureWhite
import com.example.ui.theme.TextPrimary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OddslyTopAppBar(
    title: String,
    onBackClick: (() -> Unit)? = null,
    actions: @Composable () -> Unit = {}
) {
    TopAppBar(
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (onBackClick == null) {
                    Image(
                        painter = painterResource(id = R.drawable.app_logo),
                        contentDescription = "App Logo",
                        modifier = Modifier
                            .size(30.dp)
                            .clip(RoundedCornerShape(6.dp))
                            .testTag("app_bar_logo")
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                }
                Text(
                    text = title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary
                )
            }
        },
        navigationIcon = {
            if (onBackClick != null) {
                IconButton(
                    onClick = onBackClick,
                    modifier = Modifier.testTag("app_bar_back_button")
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = TextPrimary
                    )
                }
            }
        },
        actions = {
            actions()
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = PureWhite,
            titleContentColor = TextPrimary
        )
    )
}
