package io.github.simonvar.insulter.feature.transforms

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import io.github.simonvar.insulter.api.Insult
import io.github.simonvar.insulter.api.InsultRepository
import io.github.simonvar.insulter.base.RxComposer
import io.github.simonvar.insulter.feature.data.InsultEffect
import io.github.simonvar.insulter.feature.data.InsultState
import io.github.simonvar.insulter.feature.data.InsultWish
import io.github.simonvar.insulter.feature.models.InsultLanguage
import io.github.simonvar.insulter.services.Clipboard
import io.github.simonvar.insulter.services.Share
import io.reactivex.Observable
import org.junit.Test

class InsultActorTest {

    companion object {
        private const val CORRECT_LANG = "en"
        private const val INCORRECT_LANG = "incorrect_lang"
    }

    @Test
    fun `generate insult correct lang`() {
        val actor = InsultActor(mockClipboardService(),
            mockShareService(),
            mockApiService(CORRECT_LANG, Observable.just(someInsult())),
            object : RxComposer<InsultEffect> {
                override fun compose(stream: Observable<InsultEffect>) = stream
            }
        )

        val newInsults = actor.invoke(someState(), InsultWish.LoadInsult).toList().blockingGet()
        assert(newInsults.size == 2
                && newInsults[0] is InsultEffect.StartedLoading
                && newInsults[1] is InsultEffect.LoadedInsult)
    }

    private fun someState() = InsultState("text",
        false, InsultLanguage.EN,
        false)

    private fun someInsult() = Insult(1,
        CORRECT_LANG,
        "insult",
        "author",
        100,
        "author",
        1,
        "comment")

    private fun mockApiService(lang: String, result: Observable<Insult>) = mock<InsultRepository> {
        on { generateInsult(lang) } doReturn result
    }

    private fun mockClipboardService() = mock<Clipboard> {
        on { copy(any()) }.then {  }
    }

    private fun mockShareService() = mock<Share> {
        on { share(any()) }.then {  }
    }

}