package io.github.simonvar.insulter.feature.transforms

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import io.github.simonvar.insulter.api.Insult
import io.github.simonvar.insulter.api.InsultRepository
import io.github.simonvar.insulter.feature.data.InsultEffect
import io.github.simonvar.insulter.feature.data.InsultState
import io.github.simonvar.insulter.feature.data.InsultWish
import io.github.simonvar.insulter.feature.models.InsultLanguage
import io.github.simonvar.insulter.services.Clipboard
import io.github.simonvar.insulter.services.Share
import io.reactivex.Observable
import org.junit.Test
import java.lang.Exception

class InsultActorTest {

    @Test
    fun `generate insult wish correct`() {
        val actor = mockActor(Observable.just(someInsult()))

        val newInsults = actor.invoke(someState(), InsultWish.LoadInsult).toList().blockingGet()
        assert(newInsults.size == 2
                && newInsults[0] is InsultEffect.StartedLoading
                && newInsults[1] is InsultEffect.LoadedInsult)
    }

    @Test
    fun `generate insult wish error`() {
        val actor = mockActor(Observable.error(Exception()))

        val newInsults = actor.invoke(someState(), InsultWish.LoadInsult).toList().blockingGet()
        assert(newInsults.size == 2
                && newInsults[0] is InsultEffect.StartedLoading
                && newInsults[1] is InsultEffect.ErrorLoading)
    }

    @Test
    fun `copy wish`() {
        val actor = mockActor(Observable.never())
        val copyEffect = actor.invoke(someState(), InsultWish.CopyInsult(INSULT_TEXT))
            .toList()
            .blockingGet()

        assert(copyEffect.size == 1 && copyEffect[0] is InsultEffect.CopiedInsult)
    }

    @Test
    fun `share wish`() {
        val actor = mockActor(Observable.never())
        val shareEffect = actor.invoke(someState(), InsultWish.ShareInsult(INSULT_TEXT))
            .toList()
            .blockingGet()

        assert(shareEffect.size == 1 && shareEffect[0] is InsultEffect.SharedInsult)
    }

    @Test
    fun `laguage dialog wish`() {
        val actor = mockActor(Observable.never())
        val shareEffect = actor.invoke(someState(), InsultWish.LanguageDialog)
            .toList()
            .blockingGet()

        assert(shareEffect.size == 1 && shareEffect[0] is InsultEffect.ShowLanguageDialog)
    }

    private fun someState() = InsultState("text",
        false, InsultLanguage.EN,
        false)

    private fun someInsult() = Insult(1,
        InsultLanguage.EN.literal,
        INSULT_TEXT,
        "author",
        100,
        "author",
        1,
        "comment")

    private fun mockActor(apiResult: Observable<Insult>) = InsultActor(mockClipboardService(),
        mockShareService(),
        mockApiService(apiResult)
    )

    private fun mockApiService(result: Observable<Insult>) = mock<InsultRepository> {
        on { generateInsult(any()) } doReturn result
    }

    private fun mockClipboardService() = mock<Clipboard> {
        on { copy(any()) }.then {  }
    }

    private fun mockShareService() = mock<Share> {
        on { share(any()) }.then {  }
    }

    companion object {
        private const val INSULT_TEXT = "insult"
    }

}