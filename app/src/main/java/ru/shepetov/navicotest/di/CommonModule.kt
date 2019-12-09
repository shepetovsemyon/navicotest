package ru.shepetov.navicotest.di

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.shepetov.navicotest.ui.attractionsList.AttractionListViewModel
import ru.shepetov.navicotest.ui.attractionDetails.AttractionDetailsViewModel
import java.util.*

val commonModule = module {
    single { get<Context>().packageManager }

    viewModel {
        AttractionListViewModel(get())
    }

    viewModel { AttractionDetailsViewModel() }
}
