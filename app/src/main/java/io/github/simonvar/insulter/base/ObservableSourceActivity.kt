package io.github.simonvar.insulter.base

import androidx.appcompat.app.AppCompatActivity
import io.reactivex.ObservableSource
import io.reactivex.Observer
import io.reactivex.subjects.PublishSubject

abstract class ObservableSourceActivity<T> : AppCompatActivity(),
    PublishObservableSource<T>  by ObservableSourceDelegete<T>()
