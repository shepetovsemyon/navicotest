package ru.shepetov.navicotest.ui.attractionsList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.shepetov.navicotest.State
import ru.shepetov.navicotest.data.Attraction
import ru.shepetov.navicotest.search
import ru.shepetov.navicotest.storage.AttractionsRepository


class AttractionListViewModel(private val attractionsRepository: AttractionsRepository) :
    ViewModel(), CoroutineScope by CoroutineScope(Dispatchers.Main), LoadingStateInterceptor {

    private var allAttractions = listOf<Attraction>()
    val attractionsList = MutableLiveData<List<Attraction>>()
    val clickEvent = MutableLiveData<Attraction>()
    val query = MutableLiveData<String>()

    override val errorMsg = MutableLiveData<String?>()

    override val state = MutableLiveData<State>()
        .apply {
            value = State.LOADING
        }


    init {
        fetchData()
    }

    override fun refresh() {
        fetchData()
    }

    private fun fetchData() = launch {
        allAttractions = withContext(Dispatchers.IO) {
            attractionsRepository.fetchAttractionsList()
        }

        attractionsList.value = allAttractions
        state.value = State.COMPLETED
    }

    fun search(query: String): Boolean {
        attractionsList.value = search(query, allAttractions) { listOf(it.name ?: "") }
        return true
    }
}
