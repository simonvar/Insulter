package io.github.simonvar.insulter.event

import io.github.simonvar.insulter.feature.data.InsultWish

object UiEventTransformer : (UiEvent) -> InsultWish? {

    override fun invoke(event: UiEvent) = when(event){
        is UiEvent.GenerateEvent -> InsultWish.LoadInsult
        is UiEvent.ShowLanguageDialogEvent -> InsultWish.LanguageDialog
        is UiEvent.ShareEvent -> InsultWish.ShareInsult(event.text)
        is UiEvent.CopyEvent -> InsultWish.CopyInsult(event.text)
        is UiEvent.DismissLanguageDialogEvent -> InsultWish.DismissDialog
        is UiEvent.ChangeLangEvent -> InsultWish.ChangeLang(event.lang)
    }

}