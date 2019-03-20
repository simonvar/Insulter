package io.github.simonvar.insulter.event

import io.github.simonvar.insulter.feature.data.InsultWish

object UiEventTransformer : (UiEvent) -> InsultWish? {

    override fun invoke(event: UiEvent) = when(event){
        is UiEvent.GenerateEvent -> InsultWish.LoadInsult
        is UiEvent.ShowLangugeDialogEvent -> InsultWish.LanguageDialog
        is UiEvent.ShareEvent -> InsultWish.ShareInsult(event.text)
        is UiEvent.CopyEvent -> InsultWish.CopyInsult(event.text)
    }

}