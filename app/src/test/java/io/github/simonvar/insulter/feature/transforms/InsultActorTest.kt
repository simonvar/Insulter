package io.github.simonvar.insulter.feature.transforms

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import io.github.simonvar.insulter.api.Insult
import io.github.simonvar.insulter.api.InsultRepository
import io.github.simonvar.insulter.feature.InsultFeature
import io.github.simonvar.insulter.feature.executors.InsultActorExecutorImpl
import io.github.simonvar.insulter.feature.models.InsultLanguage
import io.github.simonvar.insulter.services.Clipboard
import io.github.simonvar.insulter.services.Share
import io.reactivex.Observable
import org.junit.Test

class InsultActorTest {

    @Test
    fun `generate insult wish correct`() {
        val actor = someActor(Observable.just(someInsult()))
        val newInsults = actor.invoke(someState(), InsultFeature.Wish.Load).toList().blockingGet()

        assert(newInsults.size == 2)
        assert(newInsults[0] is InsultFeature.Effect.StartedLoading)
        assert(newInsults[1] is InsultFeature.Effect.Loaded)
    }

    @Test
    fun `generate insult wish error`() {
        val actor = someActor(Observable.error(Exception()))
        val newInsults = actor.invoke(someState(), InsultFeature.Wish.Load).toList().blockingGet()

        assert(newInsults.size == 2)
        assert(newInsults[0] is InsultFeature.Effect.StartedLoading)
        assert(newInsults[1] is InsultFeature.Effect.ErrorLoading)
    }

    @Test
    fun `copy wish`() {
        val actor = someActor(Observable.never())

        val copyEffect = actor.invoke(someState(), InsultFeature.Wish.Copy(INSULT_TEXT))
            .toList()
            .blockingGet()

        assert(copyEffect.size == 1)
        assert(copyEffect[0] is InsultFeature.Effect.Copied)
    }

    @Test
    fun `share wish`() {
        val actor = someActor(Observable.never())

        val shareEffect = actor.invoke(someState(), InsultFeature.Wish.Share(INSULT_TEXT))
            .toList()
            .blockingGet()

        assert(shareEffect.size == 1)
        assert(shareEffect[0] is InsultFeature.Effect.Shared)
    }

    @Test
    fun `language dialog wish`() {
        val actor = someActor(Observable.never())

        val shareEffect = actor.invoke(someState(), InsultFeature.Wish.LanguageDialog)
            .toList()
            .blockingGet()

        assert(shareEffect.size == 1)
        assert(shareEffect[0] is InsultFeature.Effect.ShowLanguageDialog)
    }


    private fun someActor(result: Observable<Insult>) =
        InsultFeature.ActorImpl(someActorExecutor(result))

    private fun someActorExecutor(result: Observable<Insult>) = InsultActorExecutorImpl(
        mockApiService(result),
        mockClipboardService(),
        mockShareService()
    )

    private fun mockApiService(result: Observable<Insult>) = mock<InsultRepository> {
        on { generateInsult(any()) } doReturn result
    }

    private fun mockClipboardService() = mock<Clipboard> {
        on { copy(any()) }.then { }
    }

    private fun mockShareService() = mock<Share> {
        on { share(any()) }.then { }
    }

    private fun someState() =
        InsultFeature.State("text", false, InsultLanguage.EN, false)

    private fun someInsult() =
        Insult(1, InsultLanguage.EN.literal, INSULT_TEXT, "author", 100, "author", 1, "comment")

    companion object {
        private const val INSULT_TEXT = "insult"
    }

}