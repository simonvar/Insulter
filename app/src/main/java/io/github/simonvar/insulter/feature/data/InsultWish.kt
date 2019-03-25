package io.github.simonvar.insulter.feature.data

import io.github.simonvar.insulter.feature.InsultLanguage

sealed class InsultWish {
    object LoadInsult : InsultWish()
    object LanguageDialog : InsultWish()
    object DismissDialog : InsultWish()
    data class ShareInsult(val text: String) : InsultWish()
    data class CopyInsult(val text: String) : InsultWish()
    data class ChangeLang(val lang: InsultLanguage) : InsultWish()
}