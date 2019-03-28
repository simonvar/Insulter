package io.github.simonvar.insulter.feature

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import io.github.simonvar.insulter.api.Insult
import io.github.simonvar.insulter.api.InsultRepository
import io.github.simonvar.insulter.feature.executors.InsultActorExecutorImpl
import io.github.simonvar.insulter.feature.models.InsultLanguage
import io.github.simonvar.insulter.services.Clipboard
import io.github.simonvar.insulter.services.Share
import io.reactivex.Observable
import org.junit.Assert.*
import org.junit.Test

class InsultFeatureTest {

    @Test
    fun `load insult wish normal loading`() {
        val feature = InsultFeature(someActorExecutor(Observable.just(someInsult())))
        feature.accept(InsultFeature.Wish.Load)
        assertNotNull(feature.state.text)
    }

    @Test
    fun `load insult wish long loading`() {
        val feature = InsultFeature(someActorExecutor(Observable.never()))
        feature.accept(InsultFeature.Wish.Load)
        assertNull(feature.state.text)
        assertTrue(feature.state.isLoading)
    }

    @Test
    fun `load insult wish error loading`() {
        val feature = InsultFeature(someActorExecutor(Observable.error(Exception())))
        val news = Observable.wrap(feature.news).replay()
        val disposable = news.connect()
        feature.accept(InsultFeature.Wish.Load)
        val lastNews = news.blockingFirst()
        assertTrue(lastNews is InsultFeature.News.ResponseError)
        disposable.dispose()
    }

    @Test
    fun `language dialog wish`() {
        val feature = InsultFeature(someActorExecutor(Observable.never()))
        feature.accept(InsultFeature.Wish.LanguageDialog)
        assertTrue(feature.state.isDialogShown)
    }

    @Test
    fun `open about wish`() {
        val feature = InsultFeature(someActorExecutor(Observable.never()))
        val news = Observable.wrap(feature.news).replay()
        val disposable = news.connect()
        feature.accept(InsultFeature.Wish.OpenAbout)
        val lastNews = news.blockingFirst()
        assertTrue(lastNews is InsultFeature.News.About)
        disposable.dispose()
    }

    @Test
    fun `copy wish`() {
        val feature = InsultFeature(someActorExecutor(Observable.never()))
        val news = Observable.wrap(feature.news).replay()
        val disposable = news.connect()
        feature.accept(InsultFeature.Wish.Copy(INSULT_TEXT))
        val lastNews = news.blockingFirst()
        assertTrue(lastNews is InsultFeature.News.Copied)
        disposable.dispose()
    }

    @Test
    fun `language dialog dismiss wish`() {
        val feature = InsultFeature(someActorExecutor(Observable.never()))
        feature.accept(InsultFeature.Wish.DismissDialog)
        assertFalse(feature.state.isDialogShown)
    }

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

    private fun someInsult() =
        Insult(1, InsultLanguage.EN.literal, INSULT_TEXT, "author", 100, "author", 1, "comment")

    companion object {
        private const val INSULT_TEXT = "insult"
    }
}