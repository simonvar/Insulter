package io.github.simonvar.insulter.services

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import io.github.simonvar.insulter.feature.data.InsultEffect
import io.reactivex.Observable

class ClipboardService(private val context: Context) {

    fun copy(text: String): Observable<InsultEffect> {
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val data =  ClipData.newPlainText(LABEL, text)
        clipboard.primaryClip = data
        return Observable.just(InsultEffect.CopiedInsult)
    }

    companion object {
        const val LABEL = "Evil Insult"
    }

}