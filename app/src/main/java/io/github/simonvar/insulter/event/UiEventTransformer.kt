package io.github.simonvar.insulter.event

import io.github.simonvar.insulter.feature.InsultFeature

object UiEventTransformer : (UiEvent) -> InsultFeature.Wish? {

    override fun invoke(event: UiEvent) = when(event){
        is UiEvent.GenerateEvent -> InsultFeature.Wish.Load
        is UiEvent.ShowLanguageDialogEvent -> InsultFeature.Wish.LanguageDialog
        is UiEvent.ShareEvent -> InsultFeature.Wish.Share(event.text)
        is UiEvent.CopyEvent -> InsultFeature.Wish.Copy(event.text)
        is UiEvent.DismissLanguageDialogEvent -> InsultFeature.Wish.DismissDialog
        is UiEvent.ChangeLangEvent -> InsultFeature.Wish.ChangeLang(event.lang)
        is UiEvent.OpenAbout -> InsultFeature.Wish.OpenAbout
    }

}