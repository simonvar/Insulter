package io.github.simonvar.insulter.feature

import com.badoo.mvicore.element.Actor
import io.reactivex.Observable
import io.github.simonvar.insulter.feature.InsultEffect.*
import io.reactivex.Observable.just
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class InsultActor : Actor<InsultState, InsultWish, InsultEffect> {
    //todo services

    override fun invoke(state: InsultState, wish: InsultWish) = when (wish) {

        is InsultWish.LoadInsult ->
            just(LoadedInsult("Hello"))
                .map { it as InsultEffect }
                .delay(1, TimeUnit.SECONDS)
                .startWith(just(StartedLoading))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

        is InsultWish.ShareInsult -> Observable.just(
            InsultEffect.SharedInsult
        )

        is InsultWish.CopyInsult -> Observable.just(
            InsultEffect.CopiedInsult
        )
    }
}