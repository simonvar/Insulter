package io.github.simonvar.insulter.base

import android.view.View

fun View.setOnClick(listener: () -> Unit) = setOnClickListener {
    listener()
}