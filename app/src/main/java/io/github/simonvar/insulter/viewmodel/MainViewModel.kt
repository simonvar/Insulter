package io.github.simonvar.insulter.viewmodel

import io.github.simonvar.insulter.feature.models.InsultLanguage

data class MainViewModel(
    val text: String?,
    val isLoading: Boolean,
    val isTextActionsEnabled: Boolean,
    val isDialogShown: Boolean,
    val lang: InsultLanguage
)