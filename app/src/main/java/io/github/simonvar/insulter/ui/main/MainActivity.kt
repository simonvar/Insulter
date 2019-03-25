package io.github.simonvar.insulter.ui.main

import android.os.Bundle
import android.view.View
import io.github.simonvar.insulter.feature.InsultFeature
import io.github.simonvar.insulter.R
import io.github.simonvar.insulter.event.UiEvent
import io.github.simonvar.insulter.base.ObservableSourceActivity
import io.github.simonvar.insulter.base.setOnClick
import io.github.simonvar.insulter.feature.models.InsultLanguage
import io.github.simonvar.insulter.ui.langdialog.InsultLanguageDialog
import io.github.simonvar.insulter.viewmodel.MainViewModel
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : ObservableSourceActivity<UiEvent>(), Consumer<MainViewModel> {

    private val insultFeature by inject<InsultFeature>()

    private val bindings = MainActivityBindings(
        this,
        insultFeature,
        NewsListener(this)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupView()
        bindings.setup(this)
    }

    private fun currentText() = insult_text.text.toString()

    private fun setupView(){
        insult_share.setOnClick {
            onNext(UiEvent.ShareEvent(currentText()))
        }

        insult_copy.setOnClick {
            onNext(UiEvent.CopyEvent(currentText()))
        }

        insult_generate.setOnClick {
            onNext(UiEvent.GenerateEvent)
        }

        insult_language.setOnClick {
            onNext(UiEvent.ShowLanguageDialogEvent)
        }

        insult_info.setOnClick {
            onNext(UiEvent.OpenAbout)
        }
    }

    override fun accept(viewModel: MainViewModel) {
        insult_text.text = viewModel.text
        insult_progress.visible(viewModel.isLoading)
        insult_share.isEnabled = viewModel.isTextActionsEnabled
        insult_copy.isEnabled = viewModel.isTextActionsEnabled
        dialog(viewModel.isDialogShown, viewModel.lang)
    }

    private fun dialog(isDialogShown: Boolean, lang: InsultLanguage){
        if (isDialogShown){
            val dialog = InsultLanguageDialog.instance(lang)
            dialog.show(supportFragmentManager, "LanguageDialog")
        }
    }

    private fun View.visible(isVisible: Boolean) {
        visibility = if (isVisible) View.VISIBLE else View.GONE
    }



}

