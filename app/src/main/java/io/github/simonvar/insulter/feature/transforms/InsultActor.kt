package io.github.simonvar.insulter.feature.transforms

import com.badoo.mvicore.element.Actor
import io.github.simonvar.insulter.api.Insult
import io.github.simonvar.insulter.api.InsultRepository
import io.github.simonvar.insulter.feature.data.InsultEffect
import io.github.simonvar.insulter.feature.data.InsultEffect.*
import io.github.simonvar.insulter.feature.data.InsultState
import io.github.simonvar.insulter.feature.data.InsultWish
import io.github.simonvar.insulter.services.Clipboard
import io.github.simonvar.insulter.services.Share
import io.reactivex.Observable
import io.reactivex.Observable.just

class InsultActor(
    private val clipboard: Clipboard,
    private val share: Share,
    private val api: InsultRepository
) : Actor<InsultState, InsultWish, InsultEffect> {

    override fun invoke(state: InsultState, wish: InsultWish): Observable<InsultEffect> =
        when (wish) {
            is InsultWish.LoadInsult -> api.generateInsult(state.lang.literal)
                    .map(Insult::insult)
                    .map(::LoadedInsult)
                    .cast(InsultEffect::class.java)
                    .startWith(just(StartedLoading))
                    .onErrorReturn(::ErrorLoading)

            is InsultWish.CopyInsult -> {
                clipboard.copy(wish.text)
                Observable.just(InsultEffect.CopiedInsult)
            }

            is InsultWish.ShareInsult -> {
                share.share(wish.text)
                Observable.just(InsultEffect.SharedInsult)
            }

            is InsultWish.LanguageDialog -> just(ShowLanguageDialog)

            is InsultWish.DismissDialog -> just(DismissLanguageDialog)

            is InsultWish.ChangeLang -> just(ChangedLang(wish.lang))

            is InsultWish.OpenAbout -> just(OpenAbout)
        }

}