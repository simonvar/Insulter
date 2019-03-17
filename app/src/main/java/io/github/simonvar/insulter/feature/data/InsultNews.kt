package io.github.simonvar.insulter.feature.data

sealed class InsultNews {
    data class ResponseError(val throwable: Throwable) : InsultNews()
    object Copied : InsultNews()
}