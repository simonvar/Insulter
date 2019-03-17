package io.github.simonvar.insulter.event

import io.github.simonvar.insulter.InsultWish

object UiEventTransformer : (UiEvent) -> InsultWish? {

    override fun invoke(event: UiEvent) = when(event){
        is UiEvent.GenerateEvent -> InsultWish.Loading
        is UiEvent.ShareEvent -> null
        is UiEvent.CopyEvent -> null
    }

}