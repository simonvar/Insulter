package io.github.simonvar.insulter.viewmodel

import io.github.simonvar.insulter.feature.InsultFeature

class MainViewModelTransformer : (InsultFeature.State) -> MainViewModel {

    override fun invoke(state: InsultFeature.State) = MainViewModel(
        text = state.text ?: "",
        isLoading = state.isLoading,
        isTextActionsEnabled = state.text != null,
        isDialogShown = state.isDialogShown,
        lang = state.lang
    )


}