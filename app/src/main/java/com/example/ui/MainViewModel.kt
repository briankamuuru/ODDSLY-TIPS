package com.example.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.AppDatabase
import com.example.data.InitialData
import com.example.data.PredictionEntity
import com.example.data.PreferencesManager
import com.example.localization.AppLanguage
import com.example.localization.AppStrings
import com.example.localization.LocalizationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

enum class MainTab {
    FREE_TIPS,
    VIP_TIPS,
    SETTINGS
}

enum class SubScreen {
    NONE,
    VIP_SUB_CATEGORY,
    VIP_HISTORY,
    PRIVACY_POLICY,
    ABOUT,
    ADMIN
}

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val database = AppDatabase.getDatabase(application, viewModelScope)
    private val dao = database.predictionDao()
    private val preferences = PreferencesManager(application)

    // Language state
    private val _currentLanguage = MutableStateFlow(AppLanguage.fromCode(preferences.language))
    val currentLanguage: StateFlow<AppLanguage> = _currentLanguage.asStateFlow()

    private val _strings = MutableStateFlow(LocalizationRepository.getStrings(_currentLanguage.value))
    val strings: StateFlow<AppStrings> = _strings.asStateFlow()

    // Navigation state
    private val _selectedTab = MutableStateFlow(MainTab.FREE_TIPS)
    val selectedTab: StateFlow<MainTab> = _selectedTab.asStateFlow()

    private val _currentSubScreen = MutableStateFlow(SubScreen.NONE)
    val currentSubScreen: StateFlow<SubScreen> = _currentSubScreen.asStateFlow()

    // VIP Navigation selection
    private val _selectedVipCategory = MutableStateFlow("VIP_2_PLUS")
    val selectedVipCategory: StateFlow<String> = _selectedVipCategory.asStateFlow()

    private val _selectedHistoryCategory = MutableStateFlow("VIP_2_PLUS_HISTORY")
    val selectedHistoryCategory: StateFlow<String> = _selectedHistoryCategory.asStateFlow()

    // Data streams from Room
    val freeTips: StateFlow<List<PredictionEntity>> = dao.getFreePredictionsFlow()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val vip2PlusTips: StateFlow<List<PredictionEntity>> = dao.getActiveVipPredictionsFlow("VIP_2_PLUS")
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val vip3PlusTips: StateFlow<List<PredictionEntity>> = dao.getActiveVipPredictionsFlow("VIP_3_PLUS")
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val vip5PlusTips: StateFlow<List<PredictionEntity>> = dao.getActiveVipPredictionsFlow("VIP_5_PLUS")
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val vipSpecialTips: StateFlow<List<PredictionEntity>> = dao.getActiveVipPredictionsFlow("VIP_SPECIAL")
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val vip2PlusHistory: StateFlow<List<PredictionEntity>> = dao.getVipHistoryPredictionsFlow("VIP_2_PLUS_HISTORY")
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val vip3PlusHistory: StateFlow<List<PredictionEntity>> = dao.getVipHistoryPredictionsFlow("VIP_3_PLUS_HISTORY")
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val vip5PlusHistory: StateFlow<List<PredictionEntity>> = dao.getVipHistoryPredictionsFlow("VIP_5_PLUS_HISTORY")
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val vipSpecialHistory: StateFlow<List<PredictionEntity>> = dao.getVipHistoryPredictionsFlow("VIP_SPECIAL_HISTORY")
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val allPredictions: StateFlow<List<PredictionEntity>> = dao.getAllPredictionsFlow()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    init {
        // Ensure database is populated on initial launch
        viewModelScope.launch(Dispatchers.IO) {
            AppDatabase.populateDatabase(dao)
        }
    }

    fun selectTab(tab: MainTab) {
        _selectedTab.value = tab
        _currentSubScreen.value = SubScreen.NONE
    }

    fun openVipSubCategory(category: String) {
        _selectedVipCategory.value = category
        _currentSubScreen.value = SubScreen.VIP_SUB_CATEGORY
    }

    fun openVipHistory(initialHistoryCategory: String = "VIP_2_PLUS_HISTORY") {
        _selectedHistoryCategory.value = initialHistoryCategory
        _currentSubScreen.value = SubScreen.VIP_HISTORY
    }

    fun selectHistoryCategory(category: String) {
        _selectedHistoryCategory.value = category
    }

    fun openPrivacyPolicy() {
        _currentSubScreen.value = SubScreen.PRIVACY_POLICY
    }

    fun openAbout() {
        _currentSubScreen.value = SubScreen.ABOUT
    }

    fun openAdmin() {
        _currentSubScreen.value = SubScreen.ADMIN
    }

    fun navigateBack(): Boolean {
        if (_currentSubScreen.value != SubScreen.NONE) {
            _currentSubScreen.value = SubScreen.NONE
            return true
        }
        return false
    }

    fun setLanguage(language: AppLanguage) {
        _currentLanguage.value = language
        _strings.value = LocalizationRepository.getStrings(language)
        preferences.language = language.code
    }

    /**
     * Updates prediction result and executes automatic VIP history movement:
     * - If Won or Lost on a VIP tip: moves from active list to corresponding VIP history category.
     * - If reset to Pending on a VIP tip: returns from history to active list.
     * - Updates declaration timestamp.
     */
    fun declareResult(predictionId: String, newResultState: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val prediction = dao.getPredictionById(predictionId) ?: return@launch
            val now = System.currentTimeMillis()

            val updatedPrediction = if (prediction.category == "FREE") {
                prediction.copy(
                    resultState = newResultState,
                    resultDeclarationTimestamp = if (newResultState == "PENDING") null else now
                )
            } else {
                // VIP categories
                when (newResultState) {
                    "WON", "LOST" -> {
                        prediction.copy(
                            resultState = newResultState,
                            isHistorical = true,
                            historyCategory = "${prediction.category}_HISTORY",
                            resultDeclarationTimestamp = now
                        )
                    }
                    else -> { // PENDING
                        prediction.copy(
                            resultState = "PENDING",
                            isHistorical = false,
                            historyCategory = null,
                            resultDeclarationTimestamp = null
                        )
                    }
                }
            }

            dao.update(updatedPrediction)
        }
    }

    fun resetData() {
        viewModelScope.launch(Dispatchers.IO) {
            dao.deleteAll()
            dao.insertAll(InitialData.samplePredictions)
        }
    }
}
