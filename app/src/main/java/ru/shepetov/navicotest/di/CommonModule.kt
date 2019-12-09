package ru.shepetov.navicotest.di

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.shepetov.navicotest.ui.attractionsList.AttractionsViewModel
import ru.shepetov.navicotest.ui.SharedViewModel
import ru.shepetov.navicotest.ui.attractionDetails.AttractionDetailsViewModel

val commonModule = module {
    viewModel {
        AttractionsViewModel(
            get()
        )
    }
    viewModel { AttractionDetailsViewModel() }
    viewModel { SharedViewModel() }
}
