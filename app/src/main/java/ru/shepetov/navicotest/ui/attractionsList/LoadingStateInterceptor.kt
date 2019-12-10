package ru.shepetov.navicotest.ui.attractionsList

import androidx.lifecycle.MutableLiveData
import ru.shepetov.navicotest.State

interface LoadingStateInterceptor {
    val state: MutableLiveData<State>
    val errorMsg: MutableLiveData<String?>
    fun refresh()
}