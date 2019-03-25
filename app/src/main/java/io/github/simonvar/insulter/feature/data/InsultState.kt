package io.github.simonvar.insulter.feature.data

import android.os.Parcelable
import io.github.simonvar.insulter.feature.models.InsultLanguage
import kotlinx.android.parcel.Parcelize

@Parcelize
data class InsultState(
    val text: String?,
    val isLoading: Boolean = false,
    val lang: InsultLanguage,
    val isDialogShown: Boolean = false
) : Parcelable