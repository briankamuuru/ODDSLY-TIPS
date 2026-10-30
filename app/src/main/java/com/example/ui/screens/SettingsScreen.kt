package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.AdminPanelSettings
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Numbers
import androidx.compose.material.icons.filled.Policy
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.BuildConfig
import com.example.localization.AppLanguage
import com.example.localization.AppStrings
import com.example.ui.theme.BlueBoxBg
import com.example.ui.theme.BlueBoxBorder
import com.example.ui.theme.BluePrimary
import com.example.ui.theme.PureWhite
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

@Composable
fun SettingsScreen(
    currentLanguage: AppLanguage,
    onLanguageChange: (AppLanguage) -> Unit,
    strings: AppStrings,
    onOpenPrivacyPolicy: () -> Unit,
    onOpenAbout: () -> Unit,
    onOpenAdmin: () -> Unit,
    modifier: Modifier = Modifier
) {
    var showLanguageDialog by remember { mutableStateOf(false) }

    // Obtain dynamic version name and code directly from BuildConfig
    val versionName = BuildConfig.VERSION_NAME
    val versionCode = BuildConfig.VERSION_CODE
    val versionDisplay = "$versionName ($versionCode)"

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(14.dp)
            .testTag("settings_screen"),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        // 1. Language Setting
        SettingItemCard(
            title = strings.settingLanguage,
            subtitle = currentLanguage.displayName,
            icon = Icons.Default.Language,
            tag = "setting_item_language",
            onClick = { showLanguageDialog = true }
        )

        // 2. Version Item (Display actual configured version)
        SettingItemCard(
            title = strings.settingVersion,
            subtitle = versionDisplay,
            icon = Icons.Default.Numbers,
            tag = "setting_item_version",
            showArrow = false,
            onClick = { }
        )

        // 3. Privacy Policy
        SettingItemCard(
            title = strings.settingPrivacyPolicy,
            subtitle = null,
            icon = Icons.Default.Policy,
            tag = "setting_item_privacy_policy",
            onClick = onOpenPrivacyPolicy
        )

        // 4. About
        SettingItemCard(
            title = strings.settingAbout,
            subtitle = null,
            icon = Icons.Default.Info,
            tag = "setting_item_about",
            onClick = onOpenAbout
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Dedicated Administrator Result Management tool
        SettingItemCard(
            title = strings.settingAdmin,
            subtitle = "Declare match outcomes & manage predictions",
            icon = Icons.Default.AdminPanelSettings,
            tag = "setting_item_admin",
            onClick = onOpenAdmin
        )
    }

    // Language Selection Dialog
    if (showLanguageDialog) {
        AlertDialog(
            onDismissRequest = { showLanguageDialog = false },
            title = {
                Text(
                    text = strings.selectLanguage,
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp,
                    color = TextPrimary
                )
            },
            text = {
                Column {
                    AppLanguage.entries.forEach { lang ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    onLanguageChange(lang)
                                    showLanguageDialog = false
                                }
                                .padding(vertical = 8.dp)
                                .testTag("language_option_${lang.code}"),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = currentLanguage == lang,
                                onClick = {
                                    onLanguageChange(lang)
                                    showLanguageDialog = false
                                }
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Column {
                                Text(
                                    text = lang.displayName,
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = TextPrimary
                                )
                                if (lang.displayName != lang.englishName) {
                                    Text(
                                        text = lang.englishName,
                                        fontSize = 12.sp,
                                        color = TextMuted
                                    )
                                }
                            }
                        }
                    }
                }
            },
            confirmButton = {
                TextButton(onClick = { showLanguageDialog = false }) {
                    Text(text = strings.cancel, color = BluePrimary)
                }
            },
            containerColor = PureWhite
        )
    }
}

@Composable
private fun SettingItemCard(
    title: String,
    subtitle: String?,
    icon: ImageVector,
    tag: String,
    showArrow: Boolean = true,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .testTag(tag)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = BlueBoxBg),
        border = BorderStroke(1.5.dp, BlueBoxBorder),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = BluePrimary,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(14.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = title,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = TextPrimary
                )
                if (!subtitle.isNullOrBlank()) {
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = subtitle,
                        fontSize = 12.sp,
                        color = TextSecondary
                    )
                }
            }
            if (showArrow) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = null,
                    tint = TextMuted,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}
