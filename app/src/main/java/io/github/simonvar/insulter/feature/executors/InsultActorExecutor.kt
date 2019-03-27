package io.github.simonvar.insulter.feature.executors

import io.github.simonvar.insulter.api.Insult
import io.github.simonvar.insulter.api.InsultRepository
import io.github.simonvar.insulter.feature.InsultFeature
import io.github.simonvar.insulter.services.Clipboard
import io.github.simonvar.insulter.services.Share
import io.reactivex.Observable

interface InsultActorExecutor {
    fun load(state: InsultFeature.State): Observable<InsultFeature.Effect>
    fun copy(wish: InsultFeature.Wish.Copy): Observable<InsultFeature.Effect>
    fun share(wish: InsultFeature.Wish.Share): Observable<InsultFeature.Effect>
}

class InsultActorExecutorImpl(
    private val api: InsultRepository,
    private val clipboard: Clipboard,
    private val share: Share
) : InsultActorExecutor {

    override fun load(state: InsultFeature.State): Observable<InsultFeature.Effect> =
        api.generateInsult(state.lang.literal)
            .map(Insult::insult)
            .map(InsultFeature.Effect::Loaded)
            .cast(InsultFeature.Effect::class.java)
            .startWith(Observable.just(InsultFeature.Effect.StartedLoading))
            .onErrorReturn(InsultFeature.Effect::ErrorLoading)

    override fun copy(wish: InsultFeature.Wish.Copy): Observable<InsultFeature.Effect> {
        clipboard.copy(wish.text)
        return Observable.just(InsultFeature.Effect.Copied)
    }

    override fun share(wish: InsultFeature.Wish.Share): Observable<InsultFeature.Effect> {
        share.share(wish.text)
        return Observable.just(InsultFeature.Effect.Shared)
    }
}