package io.github.simonvar.insulter.ui.langdialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.simonvar.insulter.R
import io.github.simonvar.insulter.base.ObservableSourceDialogFragment
import io.github.simonvar.insulter.event.UiEvent
import io.github.simonvar.insulter.feature.InsultFeature
import org.koin.android.ext.android.inject

class InsultLanguageDialog : ObservableSourceDialogFragment<UiEvent>() {

    private val insultFeature by inject<InsultFeature>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_languge_dialog, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        fun instance() = InsultLanguageDialog()
    }

}