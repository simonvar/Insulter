package io.github.simonvar.insulter.event

import io.github.simonvar.insulter.feature.models.InsultLanguage

sealed class UiEvent {
    object GenerateEvent : UiEvent()
    object ShowLanguageDialogEvent : UiEvent()
    object DismissLanguageDialogEvent : UiEvent()
    object OpenAbout : UiEvent()
    data class ChangeLangEvent(val lang: InsultLanguage) : UiEvent()
    data class ShareEvent(val text: String) : UiEvent()
    data class CopyEvent(val text: String) : UiEvent()
}