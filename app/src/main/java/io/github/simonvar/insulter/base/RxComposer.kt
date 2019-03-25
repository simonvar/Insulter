package io.github.simonvar.insulter.base

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers

interface RxComposer<T> {
    fun compose(stream: Observable<T>): Observable<T>
}

class ObserveMainThreadRxComposer<T> : RxComposer<T> {

    override fun compose(stream: Observable<T>) = stream.observeOn(AndroidSchedulers.mainThread())

}