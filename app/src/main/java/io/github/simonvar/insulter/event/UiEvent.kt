package io.github.simonvar.insulter.event

sealed class UiEvent {
    object GenerateEvent : UiEvent()
    data class ShareEvent(val text: String) : UiEvent()
    data class CopyEvent(val text: String) : UiEvent()
}