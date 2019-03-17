package io.github.simonvar.insulter.feature

sealed class InsultWish {
    object LoadInsult : InsultWish()
    data class ShareInsult(val text: String) : InsultWish()
    data class CopyInsult(val text: String) : InsultWish()
}