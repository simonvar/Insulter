package io.github.simonvar.insulter.feature

import android.content.Context
import com.badoo.mvicore.element.Actor
import io.github.simonvar.insulter.feature.data.InsultEffect
import io.github.simonvar.insulter.feature.data.InsultEffect.*
import io.github.simonvar.insulter.feature.data.InsultState
import io.github.simonvar.insulter.feature.data.InsultWish
import io.github.simonvar.insulter.services.ClipboardService
import io.github.simonvar.insulter.services.ShareService
import io.reactivex.Observable
import io.reactivex.Observable.just
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class InsultActor(context: Context) : Actor<InsultState, InsultWish, InsultEffect> {
    private val clipboard = ClipboardService(context)
    private val share = ShareService(context)

    override fun invoke(state: InsultState, wish: InsultWish): Observable<InsultEffect> =
        when (wish) {
            is InsultWish.LoadInsult ->
                just(LoadedInsult("Hello"))
                    .map { it as InsultEffect }
                    .delay(1, TimeUnit.SECONDS)
                    .startWith(just(StartedLoading))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())

            is InsultWish.CopyInsult -> clipboard.copy(wish.text)

            is InsultWish.ShareInsult -> share.share(wish.text)

        }
}