package io.github.simonvar.insulter.feature

import com.badoo.mvicore.element.Actor
import io.reactivex.Observable

class InsultActor : Actor<InsultState, InsultWish, InsultEffect> {
    //todo services

    override fun invoke(state: InsultState, wish: InsultWish) = when (wish) {
        is InsultWish.LoadInsult -> Observable.just(
            InsultEffect.LoadedInsult(
                "Hello"
            )
        )
        is InsultWish.ShareInsult -> Observable.just(
            InsultEffect.SharedInsult
        )
        is InsultWish.CopyInsult -> Observable.just(
            InsultEffect.CopiedInsult
        )
    }
}