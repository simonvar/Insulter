package io.github.simonvar.insulter.services

import android.content.Context
import android.content.Intent
import io.github.simonvar.insulter.feature.data.InsultEffect
import io.reactivex.Observable

interface Share {

    fun share(text: String)

}

class ShareService(private val context: Context) : Share {

    override fun share(text: String) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, text)
            type = "text/plain"
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        context.startActivity(sendIntent)
    }

}