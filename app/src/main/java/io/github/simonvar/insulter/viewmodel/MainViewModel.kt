package io.github.simonvar.insulter.viewmodel

data class MainViewModel(
    val text: String?,
    val isLoading: Boolean,
    val isTextActionsEnabled: Boolean,
    val isDialogShown: Boolean
)