package io.github.simonvar.insulter.ui

import android.content.Context
import android.widget.Toast
import io.github.simonvar.insulter.InsultNews
import io.github.simonvar.insulter.R
import io.reactivex.functions.Consumer

class NewsListener(private val context: Context) : Consumer<InsultNews> {

    override fun accept(news: InsultNews) = when (news) {
        is InsultNews.ResponseError -> error(news.throwable)
        is InsultNews.Copied -> copied()
    }

    private fun copied() =
        Toast.makeText(context, context.getString(R.string.text_copied), Toast.LENGTH_SHORT).show()

    private fun error(throwable: Throwable) =
        Toast.makeText(context, context.getString(R.string.response_error), Toast.LENGTH_SHORT).show()

}