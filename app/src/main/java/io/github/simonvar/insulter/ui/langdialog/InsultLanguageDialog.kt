package io.github.simonvar.insulter.ui.langdialog

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.simonvar.insulter.R
import io.github.simonvar.insulter.base.ObservableSourceDialogFragment
import io.github.simonvar.insulter.event.UiEvent
import io.github.simonvar.insulter.feature.InsultFeature
import io.github.simonvar.insulter.feature.models.InsultLanguage
import org.koin.android.ext.android.inject
import kotlinx.android.synthetic.main.fragment_languge_dialog.view.*


class InsultLanguageDialog : ObservableSourceDialogFragment<UiEvent>() {

    companion object {
        private const val EXTRA_LANG = "insult_language_dialog_language"
        fun instance(lang: InsultLanguage) = InsultLanguageDialog().apply {
            arguments = Bundle().apply {
                putParcelable(EXTRA_LANG, lang)
            }
        }
    }

    private val lang by lazy {
        arguments?.getParcelable(EXTRA_LANG) ?: InsultLanguage.EN
    }

    private val insultFeature by inject<InsultFeature>()
    private val bindings = InsultLanguageDialogBindings(this, insultFeature)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_languge_dialog, container, false)!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindings.setup(this)
        view.lang_radio_group.check(lang.toDialogRadioId())
        view.lang_radio_group.setOnCheckedChangeListener { _, checkedId ->
            onNext(UiEvent.ChangeLangEvent(checkedId.toInsultLanguage()))
            dismiss()
        }
    }

    override fun onDismiss(dialog: DialogInterface?) {
        super.onDismiss(dialog)
        onNext(UiEvent.DismissLanguageDialogEvent)
    }

}