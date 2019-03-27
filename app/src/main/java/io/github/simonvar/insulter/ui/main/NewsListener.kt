package io.github.simonvar.insulter.ui.main

import android.content.Context
import android.util.Log
import android.widget.Toast
import io.github.simonvar.insulter.R
import io.github.simonvar.insulter.feature.InsultFeature
import io.github.simonvar.insulter.ui.about.AboutActivity
import io.reactivex.functions.Consumer

class NewsListener(private val context: Context) : Consumer<InsultFeature.News> {

    override fun accept(news: InsultFeature.News?) {
        when (news) {
            is InsultFeature.News.ResponseError -> error(news.throwable)
            is InsultFeature.News.Copied -> copied()
            is InsultFeature.News.About -> about()
            else -> Log.d(TAG, "Another News")
        }
    }

    private fun copied() =
        Toast.makeText(context, context.getString(R.string.text_copied), Toast.LENGTH_SHORT).show()

    private fun error(throwable: Throwable) =
        Toast.makeText(context, context.getString(R.string.response_error), Toast.LENGTH_SHORT).show()

    private fun about() = context.startActivity(AboutActivity.intent(context))

    companion object {
        private const val TAG = "NewsListener"
    }
}