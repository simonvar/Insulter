package io.github.simonvar.insulter.feature.transforms

import com.badoo.mvicore.element.Actor
import io.github.simonvar.insulter.api.Insult
import io.github.simonvar.insulter.api.InsultApiService
import io.github.simonvar.insulter.feature.data.InsultEffect
import io.github.simonvar.insulter.feature.data.InsultEffect.*
import io.github.simonvar.insulter.feature.data.InsultState
import io.github.simonvar.insulter.feature.data.InsultWish
import io.github.simonvar.insulter.services.ClipboardService
import io.github.simonvar.insulter.services.ShareService
import io.reactivex.Observable
import io.reactivex.Observable.just
import io.reactivex.android.schedulers.AndroidSchedulers

class InsultActor(
    private val clipboard: ClipboardService,
    private val share: ShareService,
    private val api: InsultApiService
) : Actor<InsultState, InsultWish, InsultEffect> {

    override fun invoke(state: InsultState, wish: InsultWish): Observable<InsultEffect> =
        when (wish) {
            is InsultWish.LoadInsult ->
                api.generateInsult(state.lang.literal)
                    .map(Insult::insult)
                    .map(::LoadedInsult)
                    .cast(InsultEffect::class.java)
                    .startWith(just(StartedLoading))
                    .onErrorReturn(::ErrorLoading)
                    .observeOn(AndroidSchedulers.mainThread())

            is InsultWish.LanguageDialog -> just(ShowLanguageDialog)

            is InsultWish.DismissDialog -> just(DismissLanguageDialog)

            is InsultWish.CopyInsult -> clipboard.copy(wish.text)

            is InsultWish.ShareInsult -> share.share(wish.text)

            is InsultWish.ChangeLang -> just(ChangedLang(wish.lang))
        }
}