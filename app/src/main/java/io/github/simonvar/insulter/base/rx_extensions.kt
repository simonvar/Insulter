package io.github.simonvar.insulter.base

import io.reactivex.Observable

fun <T> Observable<T>.blockingToList() = toList().blockingGet()