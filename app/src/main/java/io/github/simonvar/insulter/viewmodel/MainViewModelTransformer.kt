package io.github.simonvar.insulter.viewmodel

import io.github.simonvar.insulter.feature.InsultState

class MainViewModelTransformer : (InsultState) -> MainViewModel {

    override fun invoke(state: InsultState) = MainViewModel(
        text = state.text ?: "",
        isLoading = state.isLoading
    )


}