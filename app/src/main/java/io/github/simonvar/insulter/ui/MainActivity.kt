package io.github.simonvar.insulter.ui

import android.os.Bundle
import android.view.View
import io.github.simonvar.insulter.feature.InsultFeature
import io.github.simonvar.insulter.R
import io.github.simonvar.insulter.event.UiEvent
import io.github.simonvar.insulter.base.ObservableSourceActivity
import io.github.simonvar.insulter.viewmodel.MainViewModel
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : ObservableSourceActivity<UiEvent>(), Consumer<MainViewModel> {

    private val insultFeature by inject<InsultFeature>()

    private val bindings = MainActivityBindings(this,
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
        insult_share.setOnClickListener {
            onNext(UiEvent.ShareEvent(currentText()))
        }

        insult_copy.setOnClickListener {
            onNext(UiEvent.CopyEvent(currentText()))
        }

        insult_generate.setOnClickListener {
            onNext(UiEvent.GenerateEvent)
        }
    }

    override fun accept(viewModel: MainViewModel) {
        insult_text.text = viewModel.text
        insult_progress.visible(viewModel.isLoading)
        insult_share.isEnabled = viewModel.isTextActionsEnabled
        insult_copy.isEnabled = viewModel.isTextActionsEnabled
    }

    private fun View.visible(isVisible: Boolean) {
        visibility = if (isVisible) View.VISIBLE else View.GONE
    }



}

