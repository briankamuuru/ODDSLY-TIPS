package com.example.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "predictions")
data class PredictionEntity(
    @PrimaryKey
    val id: String,
    val category: String, // FREE, VIP_2_PLUS, VIP_3_PLUS, VIP_5_PLUS, VIP_SPECIAL
    val homeTeam: String,
    val awayTeam: String,
    val league: String,
    val predictionText: String,
    val odds: String,
    val matchDate: String,
    val kickoffTime: String,
    val resultState: String, // PENDING, WON, LOST
    val creationTimestamp: Long,
    val resultDeclarationTimestamp: Long? = null,
    val isHistorical: Boolean = false,
    val historyCategory: String? = null // VIP_2_PLUS_HISTORY, VIP_3_PLUS_HISTORY, VIP_5_PLUS_HISTORY, VIP_SPECIAL_HISTORY
)
