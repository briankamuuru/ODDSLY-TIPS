package com.example.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FormatListBulleted
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.FormatListBulleted
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ui.components.OddslyTopAppBar
import com.example.ui.screens.AboutScreen
import com.example.ui.screens.AdminResultManagementScreen
import com.example.ui.screens.FreeTipsScreen
import com.example.ui.screens.PrivacyPolicyScreen
import com.example.ui.screens.SettingsScreen
import com.example.ui.screens.VipHistoryScreen
import com.example.ui.screens.VipSubCategoryScreen
import com.example.ui.screens.VipTipsScreen
import com.example.ui.theme.BluePrimary
import com.example.ui.theme.BluePrimaryLight
import com.example.ui.theme.PureWhite
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary

@Composable
fun OddslyApp(
    viewModel: MainViewModel = viewModel()
) {
    val currentLanguage by viewModel.currentLanguage.collectAsState()
    val strings by viewModel.strings.collectAsState()
    val selectedTab by viewModel.selectedTab.collectAsState()
    val currentSubScreen by viewModel.currentSubScreen.collectAsState()
    val selectedVipCategory by viewModel.selectedVipCategory.collectAsState()
    val selectedHistoryCategory by viewModel.selectedHistoryCategory.collectAsState()

    // Prediction data flows
    val freeTips by viewModel.freeTips.collectAsState()
    val vip2PlusTips by viewModel.vip2PlusTips.collectAsState()
    val vip3PlusTips by viewModel.vip3PlusTips.collectAsState()
    val vip5PlusTips by viewModel.vip5PlusTips.collectAsState()
    val vipSpecialTips by viewModel.vipSpecialTips.collectAsState()

    val vip2PlusHistory by viewModel.vip2PlusHistory.collectAsState()
    val vip3PlusHistory by viewModel.vip3PlusHistory.collectAsState()
    val vip5PlusHistory by viewModel.vip5PlusHistory.collectAsState()
    val vipSpecialHistory by viewModel.vipSpecialHistory.collectAsState()

    val allPredictions by viewModel.allPredictions.collectAsState()

    // Handle system back navigation
    BackHandler(enabled = currentSubScreen != SubScreen.NONE) {
        viewModel.navigateBack()
    }

    val layoutDirection = if (currentLanguage.isRtl) LayoutDirection.Rtl else LayoutDirection.Ltr

    CompositionLocalProvider(LocalLayoutDirection provides layoutDirection) {
        val topAppBarTitle = when (currentSubScreen) {
            SubScreen.NONE -> when (selectedTab) {
                MainTab.FREE_TIPS -> strings.navFreeTips
                MainTab.VIP_TIPS -> strings.navVipTips
                MainTab.SETTINGS -> strings.navSettings
            }
            SubScreen.VIP_SUB_CATEGORY -> when (selectedVipCategory) {
                "VIP_2_PLUS" -> strings.vip2PlusTitle
                "VIP_3_PLUS" -> strings.vip3PlusTitle
                "VIP_5_PLUS" -> strings.vip5PlusTitle
                "VIP_SPECIAL" -> strings.vipSpecialTitle
                else -> strings.navVipTips
            }
            SubScreen.VIP_HISTORY -> strings.vipHistoryTitle
            SubScreen.PRIVACY_POLICY -> strings.privacyPolicyTitle
            SubScreen.ABOUT -> strings.aboutTitle
            SubScreen.ADMIN -> strings.adminTitle
        }

        Scaffold(
            topBar = {
                OddslyTopAppBar(
                    title = topAppBarTitle,
                    onBackClick = if (currentSubScreen != SubScreen.NONE) {
                        { viewModel.navigateBack() }
                    } else null
                )
            },
            bottomBar = {
                // Show bottom navigation bar on root screens
                if (currentSubScreen == SubScreen.NONE) {
                    NavigationBar(
                        containerColor = PureWhite,
                        tonalElevation = 4.dp,
                        modifier = Modifier.testTag("bottom_nav_bar")
                    ) {
                        // 1. Free Tips
                        NavigationBarItem(
                            selected = selectedTab == MainTab.FREE_TIPS,
                            onClick = { viewModel.selectTab(MainTab.FREE_TIPS) },
                            icon = {
                                Icon(
                                    imageVector = if (selectedTab == MainTab.FREE_TIPS) Icons.Filled.FormatListBulleted else Icons.Outlined.FormatListBulleted,
                                    contentDescription = strings.navFreeTips,
                                    modifier = Modifier.size(20.dp)
                                )
                            },
                            label = {
                                Text(
                                    text = strings.navFreeTips,
                                    fontSize = 12.sp,
                                    fontWeight = if (selectedTab == MainTab.FREE_TIPS) FontWeight.Bold else FontWeight.Medium
                                )
                            },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = BluePrimary,
                                selectedTextColor = BluePrimary,
                                indicatorColor = BluePrimaryLight,
                                unselectedIconColor = TextMuted,
                                unselectedTextColor = TextMuted
                            ),
                            modifier = Modifier.testTag("nav_item_free_tips")
                        )

                        // 2. VIP Tips
                        NavigationBarItem(
                            selected = selectedTab == MainTab.VIP_TIPS,
                            onClick = { viewModel.selectTab(MainTab.VIP_TIPS) },
                            icon = {
                                Icon(
                                    imageVector = if (selectedTab == MainTab.VIP_TIPS) Icons.Filled.Star else Icons.Outlined.StarBorder,
                                    contentDescription = strings.navVipTips,
                                    modifier = Modifier.size(20.dp)
                                )
                            },
                            label = {
                                Text(
                                    text = strings.navVipTips,
                                    fontSize = 12.sp,
                                    fontWeight = if (selectedTab == MainTab.VIP_TIPS) FontWeight.Bold else FontWeight.Medium
                                )
                            },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = BluePrimary,
                                selectedTextColor = BluePrimary,
                                indicatorColor = BluePrimaryLight,
                                unselectedIconColor = TextMuted,
                                unselectedTextColor = TextMuted
                            ),
                            modifier = Modifier.testTag("nav_item_vip_tips")
                        )

                        // 3. Settings
                        NavigationBarItem(
                            selected = selectedTab == MainTab.SETTINGS,
                            onClick = { viewModel.selectTab(MainTab.SETTINGS) },
                            icon = {
                                Icon(
                                    imageVector = if (selectedTab == MainTab.SETTINGS) Icons.Filled.Settings else Icons.Outlined.Settings,
                                    contentDescription = strings.navSettings,
                                    modifier = Modifier.size(20.dp)
                                )
                            },
                            label = {
                                Text(
                                    text = strings.navSettings,
                                    fontSize = 12.sp,
                                    fontWeight = if (selectedTab == MainTab.SETTINGS) FontWeight.Bold else FontWeight.Medium
                                )
                            },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = BluePrimary,
                                selectedTextColor = BluePrimary,
                                indicatorColor = BluePrimaryLight,
                                unselectedIconColor = TextMuted,
                                unselectedTextColor = TextMuted
                            ),
                            modifier = Modifier.testTag("nav_item_settings")
                        )
                    }
                }
            },
            containerColor = PureWhite
        ) { innerPadding ->
            val contentModifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)

            when (currentSubScreen) {
                SubScreen.VIP_SUB_CATEGORY -> {
                    val activeCategoryPredictions = when (selectedVipCategory) {
                        "VIP_2_PLUS" -> vip2PlusTips
                        "VIP_3_PLUS" -> vip3PlusTips
                        "VIP_5_PLUS" -> vip5PlusTips
                        "VIP_SPECIAL" -> vipSpecialTips
                        else -> emptyList()
                    }
                    val catTitle = when (selectedVipCategory) {
                        "VIP_2_PLUS" -> strings.vip2PlusTitle
                        "VIP_3_PLUS" -> strings.vip3PlusTitle
                        "VIP_5_PLUS" -> strings.vip5PlusTitle
                        "VIP_SPECIAL" -> strings.vipSpecialTitle
                        else -> strings.navVipTips
                    }
                    VipSubCategoryScreen(
                        title = catTitle,
                        predictions = activeCategoryPredictions,
                        strings = strings,
                        modifier = contentModifier
                    )
                }

                SubScreen.VIP_HISTORY -> {
                    val historyPredictions = when (selectedHistoryCategory) {
                        "VIP_2_PLUS_HISTORY" -> vip2PlusHistory
                        "VIP_3_PLUS_HISTORY" -> vip3PlusHistory
                        "VIP_5_PLUS_HISTORY" -> vip5PlusHistory
                        "VIP_SPECIAL_HISTORY" -> vipSpecialHistory
                        else -> emptyList()
                    }
                    VipHistoryScreen(
                        selectedCategory = selectedHistoryCategory,
                        onSelectCategory = { cat -> viewModel.selectHistoryCategory(cat) },
                        predictions = historyPredictions,
                        strings = strings,
                        modifier = contentModifier
                    )
                }

                SubScreen.PRIVACY_POLICY -> {
                    PrivacyPolicyScreen(
                        strings = strings,
                        modifier = contentModifier
                    )
                }

                SubScreen.ABOUT -> {
                    AboutScreen(
                        strings = strings,
                        modifier = contentModifier
                    )
                }

                SubScreen.ADMIN -> {
                    AdminResultManagementScreen(
                        predictions = allPredictions,
                        onDeclareResult = { id, res -> viewModel.declareResult(id, res) },
                        onResetData = { viewModel.resetData() },
                        strings = strings,
                        modifier = contentModifier
                    )
                }

                SubScreen.NONE -> {
                    when (selectedTab) {
                        MainTab.FREE_TIPS -> {
                            FreeTipsScreen(
                                predictions = freeTips,
                                strings = strings,
                                modifier = contentModifier
                            )
                        }
                        MainTab.VIP_TIPS -> {
                            VipTipsScreen(
                                strings = strings,
                                onSelectVipSubCategory = { cat -> viewModel.openVipSubCategory(cat) },
                                onSelectHistoryVip = { viewModel.openVipHistory() },
                                modifier = contentModifier
                            )
                        }
                        MainTab.SETTINGS -> {
                            SettingsScreen(
                                currentLanguage = currentLanguage,
                                onLanguageChange = { lang -> viewModel.setLanguage(lang) },
                                strings = strings,
                                onOpenPrivacyPolicy = { viewModel.openPrivacyPolicy() },
                                onOpenAbout = { viewModel.openAbout() },
                                onOpenAdmin = { viewModel.openAdmin() },
                                modifier = contentModifier
                            )
                        }
                    }
                }
            }
        }
    }
}
