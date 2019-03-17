package io.github.simonvar.insulter.ui

import android.os.Bundle
import io.github.simonvar.insulter.InsultFeature
import io.github.simonvar.insulter.InsultState
import io.github.simonvar.insulter.R
import io.github.simonvar.insulter.event.UiEvent
import io.github.simonvar.insulter.base.ObservableSourceActivity
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : ObservableSourceActivity<UiEvent>(), Consumer<InsultState> {

    private val bindings = MainActivityBindings(this,
        InsultFeature(),
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

    override fun accept(state: InsultState) {
        if (state.text != null) insult_text.text = state.text
    }

}

