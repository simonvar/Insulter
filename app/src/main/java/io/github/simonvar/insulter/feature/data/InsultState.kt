package io.github.simonvar.insulter.feature.data

import android.os.Parcelable
import io.github.simonvar.insulter.feature.InsultLanguage
import kotlinx.android.parcel.Parcelize

@Parcelize
data class InsultState(
    val text: String?,
    val isLoading: Boolean,
    val lang: InsultLanguage
) : Parcelable