package io.github.simonvar.insulter.feature.transforms

import com.badoo.mvicore.element.Reducer
import io.github.simonvar.insulter.feature.data.InsultEffect
import io.github.simonvar.insulter.feature.data.InsultState

class InsultReducer : Reducer<InsultState, InsultEffect> {
    override fun invoke(state: InsultState, effect: InsultEffect) = when (effect) {
        is InsultEffect.StartedLoading -> state.copy(text = null, isLoading = true)
        is InsultEffect.LoadedInsult -> state.copy(text = effect.text, isLoading = false)
        is InsultEffect.ErrorLoading -> state.copy(isLoading = false)
        is InsultEffect.ShowLanguageDialog -> state.copy(isDialogShown = true)
        is InsultEffect.DismissLanguageDialog -> state.copy(isDialogShown = false)
        is InsultEffect.ChangedLang -> state.copy(lang = effect.lang, isDialogShown = false)
        else -> state.copy()
    }
}