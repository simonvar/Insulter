package io.github.simonvar.insulter.feature

import android.os.Parcelable
import com.badoo.mvicore.element.Actor
import com.badoo.mvicore.element.NewsPublisher
import com.badoo.mvicore.element.Reducer
import com.badoo.mvicore.feature.ActorReducerFeature
import io.github.simonvar.insulter.feature.executors.InsultActorExecutor
import io.github.simonvar.insulter.feature.models.InsultLanguage
import io.reactivex.Observable
import io.reactivex.Observable.just
import kotlinx.android.parcel.Parcelize

class InsultFeature(
    actorExecutor: InsultActorExecutor
) : ActorReducerFeature<InsultFeature.Wish, InsultFeature.Effect, InsultFeature.State, InsultFeature.News>(
    initialState = State(null, false, InsultLanguage.EN),
    actor = ActorImpl(actorExecutor),
    reducer = ReducerImpl(),
    newsPublisher = NewsPublisherImpl()
) {

    @Parcelize
    data class State(
        val text: String?,
        val isLoading: Boolean = false,
        val lang: InsultLanguage,
        val isDialogShown: Boolean = false
    ) : Parcelable

    class ActorImpl(private val executor: InsultActorExecutor) :
        Actor<State, Wish, Effect> {

        override fun invoke(state: State, wish: Wish): Observable<Effect> =
            when (wish) {
                is Wish.Load -> executor.load(state)
                is Wish.Copy -> executor.copy(wish)
                is Wish.Share -> executor.share(wish)
                is Wish.LanguageDialog -> just(Effect.ShowLanguageDialog)
                is Wish.DismissDialog -> just(Effect.DismissLanguageDialog)
                is Wish.ChangeLang -> just(Effect.ChangedLang(wish.lang))
                is Wish.OpenAbout -> just(Effect.OpenAbout)
            }
    }

    private class ReducerImpl : Reducer<State, Effect> {
        override fun invoke(state: State, effect: Effect) = when (effect) {
            is Effect.StartedLoading -> state.copy(text = null, isLoading = true)
            is Effect.Loaded -> state.copy(text = effect.text, isLoading = false)
            is Effect.ErrorLoading -> state.copy(isLoading = false)
            is Effect.ShowLanguageDialog -> state.copy(isDialogShown = true)
            is Effect.DismissLanguageDialog -> state.copy(isDialogShown = false)
            is Effect.ChangedLang -> state.copy(lang = effect.lang, isDialogShown = false)
            else -> state.copy()
        }
    }

    private class NewsPublisherImpl : NewsPublisher<Wish, Effect, State, News> {
        override fun invoke(wish: Wish, effect: Effect, state: State): News? =
            when (effect) {
                is Effect.ErrorLoading -> News.ResponseError(effect.throwable)
                is Effect.Copied -> News.Copied
                is Effect.OpenAbout -> News.About
                else -> null
            }
    }

    sealed class Wish {
        object Load : Wish()
        object LanguageDialog : Wish()
        object DismissDialog : Wish()
        object OpenAbout : Wish()
        data class Share(val text: String) : Wish()
        data class Copy(val text: String) : Wish()
        data class ChangeLang(val lang: InsultLanguage) : Wish()
    }

    sealed class Effect {
        object StartedLoading : Effect()
        data class Loaded(val text: String) : Effect()
        data class ErrorLoading(val throwable: Throwable) : Effect()
        object Copied : Effect()
        object Shared : Effect()
        data class ChangedLang(val lang: InsultLanguage) : Effect()
        object ShowLanguageDialog : Effect()
        object DismissLanguageDialog : Effect()
        object OpenAbout : Effect()
    }

    sealed class News {
        object Copied : News()
        object About : News()
        data class ResponseError(val throwable: Throwable) : News()
    }

}