package io.github.simonvar.insulter.ui.main

import android.content.Context
import android.util.Log
import android.widget.Toast
import io.github.simonvar.insulter.feature.data.InsultNews
import io.github.simonvar.insulter.R
import io.reactivex.functions.Consumer

class NewsListener(private val context: Context) : Consumer<InsultNews> {

    override fun accept(news: InsultNews?) {
        when (news) {
            is InsultNews.ResponseError -> error(news.throwable)
            is InsultNews.Copied -> copied()
            else -> Log.d(TAG, "Another News")
        }
    }

    private fun copied() =
        Toast.makeText(context, context.getString(R.string.text_copied), Toast.LENGTH_SHORT).show()

    private fun error(throwable: Throwable) =
        Toast.makeText(context, context.getString(R.string.response_error), Toast.LENGTH_SHORT).show()

    companion object {
        private const val TAG = "NewsListener"
    }
}