package io.github.simonvar.insulter.feature

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
enum class InsultLanguage(val literal: String) : Parcelable {
    RU("ru"), EN("en"), DE("de"), FR("fr")
}