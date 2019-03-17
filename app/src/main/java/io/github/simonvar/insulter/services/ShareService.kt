package io.github.simonvar.insulter.services

import android.content.Context
import android.content.Intent
import io.github.simonvar.insulter.feature.data.InsultEffect
import io.reactivex.Observable

class ShareService(private val context: Context) {

    fun share(text: String): Observable<InsultEffect> {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, text)
            type = "text/plain"
        }
        context.startActivity(sendIntent)
        return Observable.just(InsultEffect.SharedInsult)
    }

}