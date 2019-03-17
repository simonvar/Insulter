package io.github.simonvar.insulter

import com.badoo.mvicore.element.Actor
import com.badoo.mvicore.element.NewsPublisher
import com.badoo.mvicore.element.Reducer
import com.badoo.mvicore.feature.ActorReducerFeature
import io.reactivex.Observable

class InsultFeature : ActorReducerFeature<InsultWish, InsultEffect, InsultState, InsultNews>(
    initialState = InsultState(null, false),
    actor = InsultActor(),
    reducer = InsultReducer(),
    newsPublisher = InsultNewsPublisher()
)

data class InsultState(
    val text: String?,
    val isLoading: Boolean
)

sealed class InsultEffect {
    object StartedLoading : InsultEffect()
    object CopiedInsult : InsultEffect()
    object SharedInsult : InsultEffect()
    data class LoadedInsult(val text: String) : InsultEffect()
    data class ErrorLoading(val throwable : Throwable) : InsultEffect()
}

sealed class InsultWish {
    object LoadInsult : InsultWish()
    data class ShareInsult(val text: String) : InsultWish()
    data class CopyInsult(val text: String) : InsultWish()
}


sealed class InsultNews {
    data class ResponseError(val throwable: Throwable) : InsultNews()
    object Copied : InsultNews()
}

class InsultActor : Actor<InsultState, InsultWish, InsultEffect> {
    //todo services

    override fun invoke(state: InsultState, wish: InsultWish) = when (wish) {
        is InsultWish.LoadInsult -> Observable.just(InsultEffect.LoadedInsult("Hello"))
        is InsultWish.ShareInsult -> Observable.just(InsultEffect.SharedInsult)
        is InsultWish.CopyInsult -> Observable.just(InsultEffect.CopiedInsult)
    }
}

class InsultReducer : Reducer<InsultState, InsultEffect> {
    override fun invoke(state: InsultState, effect: InsultEffect) = when(effect){
        is InsultEffect.StartedLoading -> state.copy(text = null, isLoading = true)
        is InsultEffect.LoadedInsult -> state.copy(text = effect.text, isLoading = false)
        is InsultEffect.ErrorLoading -> state.copy(isLoading = false)
        else -> state.copy()
    }
}

class InsultNewsPublisher : NewsPublisher<InsultWish, InsultEffect, InsultState, InsultNews> {
    override fun invoke(wish: InsultWish, effect: InsultEffect, state: InsultState) = when (effect) {
        is InsultEffect.ErrorLoading -> InsultNews.ResponseError(effect.throwable)
        is InsultEffect.LoadedInsult -> InsultNews.Copied
        else -> null
    }

}