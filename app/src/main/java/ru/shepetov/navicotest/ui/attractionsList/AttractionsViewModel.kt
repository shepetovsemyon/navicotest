package ru.shepetov.navicotest.ui.attractionsList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.shepetov.navicotest.State
import ru.shepetov.navicotest.data.Attraction
import ru.shepetov.navicotest.storage.AttractionsRepository


class AttractionsViewModel(private val attractionsRepository: AttractionsRepository) :
    ViewModel(), CoroutineScope by CoroutineScope(Dispatchers.Main) {

    val attractionsList = MutableLiveData<List<Attraction>>()
    val clickEvent = MutableLiveData<Attraction?>()

    val state = MutableLiveData<State>()
        .apply {
            value = State.LOADING
        }

    fun fetchData() = launch {
        attractionsList.value = withContext(Dispatchers.IO) {
            attractionsRepository.fetchAttractionsList()
        }

        state.value = State.COMPLETED
    }
}

