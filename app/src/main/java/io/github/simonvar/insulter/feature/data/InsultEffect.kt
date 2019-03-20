package io.github.simonvar.insulter.feature.data

sealed class InsultEffect {
    object StartedLoading : InsultEffect()
    object CopiedInsult : InsultEffect()
    object SharedInsult : InsultEffect()
    object ShowLangugeDialog : InsultEffect()
    data class LoadedInsult(val text: String) : InsultEffect()
    data class ErrorLoading(val throwable : Throwable) : InsultEffect()
}