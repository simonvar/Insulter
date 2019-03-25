package io.github.simonvar.insulter.feature.data

import io.github.simonvar.insulter.feature.models.InsultLanguage

sealed class InsultEffect {
    object StartedLoading : InsultEffect()
    object CopiedInsult : InsultEffect()
    object SharedInsult : InsultEffect()
    object ShowLanguageDialog : InsultEffect()
    object DismissLanguageDialog : InsultEffect()
    data class ChangedLang(val lang: InsultLanguage) : InsultEffect()
    data class LoadedInsult(val text: String) : InsultEffect()
    data class ErrorLoading(val throwable : Throwable) : InsultEffect()
}