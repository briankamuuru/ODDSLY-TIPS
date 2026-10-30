package com.example.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.data.PredictionEntity
import com.example.localization.AppStrings
import com.example.ui.components.DateGroupedPredictionsList

@Composable
fun FreeTipsScreen(
    predictions: List<PredictionEntity>,
    strings: AppStrings,
    modifier: Modifier = Modifier
) {
    DateGroupedPredictionsList(
        predictions = predictions,
        strings = strings,
        emptyMessage = strings.emptyTips,
        modifier = modifier,
        testTagPrefix = "free_tips"
    )
}
