package ru.shepetov.navicotest.ui.attractionDetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.shepetov.navicotest.data.Attraction

class AttractionDetailsViewModel : ViewModel() {
    val attraction = MutableLiveData<Attraction>()
}
