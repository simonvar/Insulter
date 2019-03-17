package io.github.simonvar.insulter.ui

import com.badoo.mvicore.android.AndroidBindings
import com.badoo.mvicore.binder.named
import com.badoo.mvicore.binder.using
import io.github.simonvar.insulter.InsultFeature
import io.github.simonvar.insulter.event.UiEventTransformer

class MainActivityBindings(
    view: MainActivity,
    private val feature: InsultFeature,
    private val newsListener: NewsListener
) : AndroidBindings<MainActivity>(view) {

    override fun setup(view: MainActivity) {
        binder.bind(view to feature using UiEventTransformer)
        binder.bind(feature.news to newsListener named "MainActivity.News")
    }

}