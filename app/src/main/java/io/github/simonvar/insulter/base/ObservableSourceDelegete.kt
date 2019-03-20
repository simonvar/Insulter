package io.github.simonvar.insulter.base

import io.reactivex.ObservableSource
import io.reactivex.Observer
import io.reactivex.subjects.PublishSubject

interface PublishObservableSource<T> : ObservableSource<T> {

    fun onNext(t: T)

}

class ObservableSourceDelegete<T> : PublishObservableSource<T> {

    private val source = PublishSubject.create<T>()

    override fun onNext(t: T) {
        source.onNext(t)
    }

    override fun subscribe(observer: Observer<in T>) {
        source.subscribe(observer)
    }

}