package io.github.simonvar.insulter.services

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import io.github.simonvar.insulter.feature.data.InsultEffect
import io.reactivex.Observable

interface Clipboard {
    fun copy(text: String)
}

class ClipboardService(private val context: Context) : Clipboard {

    override fun copy(text: String){
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val data =  ClipData.newPlainText(LABEL, text)
        clipboard.primaryClip = data
    }

    companion object {
        const val LABEL = "Evil Insult"
    }

}