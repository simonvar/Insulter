package io.github.simonvar.insulter.feature.data

sealed class InsultWish {
    object LoadInsult : InsultWish()
    object LanguageDialog : InsultWish()
    data class ShareInsult(val text: String) : InsultWish()
    data class CopyInsult(val text: String) : InsultWish()
}