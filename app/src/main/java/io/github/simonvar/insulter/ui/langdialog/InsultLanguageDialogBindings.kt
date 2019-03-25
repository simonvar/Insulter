package io.github.simonvar.insulter.ui.langdialog

import com.badoo.mvicore.android.AndroidBindings
import com.badoo.mvicore.binder.using
import io.github.simonvar.insulter.event.UiEventTransformer
import io.github.simonvar.insulter.feature.InsultFeature

class InsultLanguageDialogBindings(
    view: InsultLanguageDialog,
    private val feature: InsultFeature
) : AndroidBindings<InsultLanguageDialog>(view) {

    override fun setup(view: InsultLanguageDialog) {
        binder.bind(view to feature using UiEventTransformer)
    }

}