package io.github.simonvar.insulter.ui.about

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.github.simonvar.insulter.R
import io.github.simonvar.insulter.base.setOnClick
import kotlinx.android.synthetic.main.activity_about.*
import android.content.Intent
import android.net.Uri


class AboutActivity : AppCompatActivity() {

    companion object {
        fun intent(context: Context) = Intent(context, AboutActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        setupView()
    }

    private fun setupView(){
        home.setOnClick(::onBackPressed)
        yaroslavok.setOnClick(::openYaroslavok)
        simonvar.setOnClick(::openSimonvar)
        badoo.setOnClick(::openMviBadoo)
        evilinsult.setOnClick(::openInsulter)
    }

    private fun openLink(link: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
        startActivity(browserIntent)
    }

    private fun openYaroslavok() = openLink("https://github.com/yaroslavok")

    private fun openSimonvar() = openLink("https://github.com/simonvar")

    private fun openMviBadoo() = openLink("https://github.com/badoo/MVICore")

    private fun openInsulter() = openLink("https://github.com/EvilInsultGenerator/")
}