package io.github.simonvar.insulter.ui.langdialog

import io.github.simonvar.insulter.R
import io.github.simonvar.insulter.feature.models.InsultLanguage

fun InsultLanguage.toDialogRadioId() = when(this) {
    InsultLanguage.RU -> R.id.radio_ru
    InsultLanguage.EN -> R.id.radio_en
    InsultLanguage.DE -> R.id.radio_de
    InsultLanguage.FR -> R.id.radio_fr
}

fun Int.toInsultLanguage() = when(this){
    R.id.radio_ru -> InsultLanguage.RU
    R.id.radio_en -> InsultLanguage.EN
    R.id.radio_de -> InsultLanguage.DE
    R.id.radio_fr -> InsultLanguage.FR
    else -> InsultLanguage.EN
}