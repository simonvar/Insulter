package io.github.simonvar.insulter.viewmodel

import io.github.simonvar.insulter.feature.data.InsultState

class MainViewModelTransformer : (InsultState) -> MainViewModel {

    override fun invoke(state: InsultState) = MainViewModel(
        text = state.text ?: "",
        isLoading = state.isLoading,
        isTextActionsEnabled = state.text != null,
        isDialogShown = state.isDialogShown,
        lang = state.lang
    )


}