package ru.shepetov.navicotest

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

fun <T> MutableLiveData<T>.observeOnes(owner: LifecycleOwner, observer: Observer<T> ) {
    observe(owner, Observer {
        if (value == null) return@Observer
        observer.onChanged(it)
        value = null
    })
}