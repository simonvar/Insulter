package io.github.simonvar.insulter.base

import androidx.fragment.app.DialogFragment

abstract class ObservableSourceDialogFragment<T>: DialogFragment(),
    PublishObservableSource<T> by ObservableSourceDelegete<T>()