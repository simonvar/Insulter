package io.github.simonvar.insulter

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.badoo.mvicore.android.AndroidBinderLifecycle
import com.badoo.mvicore.binder.Binder
import io.github.simonvar.insulter.base.ObservableSourceActivity
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable

class MainActivity : ObservableSourceActivity<UiEvent>() {

    private val composite = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val feature = InsultFeature()
        Observable.wrap(feature).subscribe(::handleState).run(composite::add)

        val binder = binder()
    }

    private fun handleState(state: InsultState){

    }

}

fun FragmentActivity.binder() = Binder(AndroidBinderLifecycle(lifecycle))
